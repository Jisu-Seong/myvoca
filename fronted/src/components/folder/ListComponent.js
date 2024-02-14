import { useEffect, useState } from "react";
import useCustomMove from "../../hooks/useCustomMove";
import jwtAxios from "./../../util/jwtUtil";
import { getList } from "../../api/folderApi";

const ListComponent = () => {
  const [serverData, setServerData] = useState([]);
  const { moveToList, refresh, moveToRead } = useCustomMove();

  useEffect(() => {
    getList().then((data) => {
      setServerData(data);
    });
  }, [refresh]);

  return (
    <div className="border-2 border-blue-100 mt-10 mr-2 ml-2">
      <div className="flex flex-wrap mx-auto justify-center p-6">
        {serverData ? (
          serverData.map((folder) => (
            <div
              key={folder.fid}
              className="w-full min-w-[400px] p-2 m-2 rounded shadow-md"
              onClick={() => moveToRead(folder.fid)}
            >
              <div className="flex ">
                <div className="font-extrabold text-2xl p-2 w-1/12">
                  {folder.fid}
                </div>
                <div className="text-1xl m-1 p-2 w-8/12 font-extrabold">
                  {folder.foldername}
                </div>
                <div className="text-1xl m-1 p-2 w-2/10 font-medium">
                  {folder.updatedAt}
                </div>
              </div>
            </div>
          ))
        ) : (
          <div className="text-1xl m-1 p-2 w-8/12 font-extrabold">
            데이터가 없어요!
          </div>
        )}
      </div>
    </div>
  );
};

export default ListComponent;