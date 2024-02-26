import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import useCustomMove from "../../hooks/useCustomMove";
import jwtAxios from "./../../util/jwtUtil";
import FetchingModal from "../common/FetchingModal";
import useCustomLogin from "./../../hooks/useCustomLogin";
import { getList } from "../../api/vocaApi";

const ListComponent = () => {
  const { fid } = useParams();
  const [serverData, setServerData] = useState([]);
  const { moveToList, refresh, moveToRead, moveToVocaList } = useCustomMove();
  const { exceptionHandle } = useCustomLogin();

  useEffect(() => {
    getList(fid)
      .then((result) => {
        if (result) {
          console.log(result.data.dtoList);
          setServerData(result.data.dtoList);
        }
      })
      .catch((err) => exceptionHandle(err));
  }, [refresh]);

  return (
    <div className="border-2 border-blue-100 mt-10 mr-2 ml-2">
      <div className="flex flex-wrap mx-auto justify-center p-6">
        {serverData ? (
          serverData.map((voca) => (
            <div
              key={voca.vid}
              className="w-full min-w-[400px] p-2 m-2 rounded shadow-md"
            >
              <div className="flex ">
                <div className="font-extrabold text-2xl p-2 w-1/12">
                  {voca.vid}
                </div>
                <div className="text-1xl m-1 p-2 w-8/12 font-extrabold">
                  {voca.vocaname}
                </div>
                <div className="text-1xl m-1 p-2 w-2/10 font-medium">
                  {voca.meaning}
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
