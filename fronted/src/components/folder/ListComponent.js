import { useEffect, useState } from "react";
import useCustomMove from "../../hooks/useCustomMove";
import jwtAxios from "./../../util/jwtUtil";
import { getList } from "../../api/folderApi";
import FetchingModal from "../common/FetchingModal";
import useCustomLogin from "./../../hooks/useCustomLogin";

const ListComponent = () => {
  const [serverData, setServerData] = useState([]);
  const { moveToList, refresh, moveToRead, moveToVocaList } = useCustomMove();
  const { exceptionHandle } = useCustomLogin();

  useEffect(() => {
    getList()
      .then((data) => {
        setServerData(data);
      })
      .catch((err) => exceptionHandle(err));
  }, [refresh]);

  return (
    <div className=" mt-10 mr-2 ml-2">
      <div className="text-xl flex flex-wrap mx-auto justify-center p-6">
        {serverData ? (
          serverData.map((folder) => (
            <div
              key={folder.fid}
              className="w-full min-w-[400px] p-2 m-2 rounded shadow-md  bg-white"
              onClick={() => moveToVocaList(folder.foldername)}
            >
              <div className="flex  ">
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
