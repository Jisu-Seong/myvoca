import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import useCustomMove from "../../hooks/useCustomMove";
import jwtAxios from "./../../util/jwtUtil";
import FetchingModal from "../common/FetchingModal";
import useCustomLogin from "./../../hooks/useCustomLogin";
import { getList, getListAll } from "../../api/vocaApi";

const ListComponent = () => {
  const { fid } = useParams();
  const [serverData, setServerData] = useState([]);
  const { moveToList, refresh, moveToVocaRead, moveToVocaList } =
    useCustomMove();
  const { exceptionHandle } = useCustomLogin();

  useEffect(() => {
    if (fid === undefined) {
      console.log("fid is undefined");
    } else {
      getList(fid)
        .then((result) => {
          if (result) {
            console.log(result.data.dtoList);
            setServerData(result.data.dtoList);
          }
        })
        .catch((err) => exceptionHandle(err));
    }
  }, [refresh]);

  return (
    <div className="mx-10 text-3xl">
      Voca List - folder {fid}
      <div className="text-xl flex flex-wrap mx-auto justify-centers m-5">
        {serverData ? (
          serverData.map((voca) => (
            <div
              key={voca.vid}
              className="w-full min-w-[400px] p-2 m-2 rounded shadow-md bg-white"
              onClick={() => moveToVocaRead(voca.vid, voca.fid)}
            >
              <div className="flex ">
                <div className="w-1/2 text-left">
                  <div className="m-1 p-2 w-fullfont-extrabold align-middle bg-white">
                    {voca.vocaname}
                  </div>
                </div>
                <div className="w-1/2">
                  <div className="m-1 p-2 w-full font-medium bg-white">
                    {voca.meaning}
                  </div>
                </div>
              </div>
            </div>
          ))
        ) : (
          <div className="text-1xl m-1 p-2 w-full font-extrabold">
            데이터가 없어요!
          </div>
        )}
      </div>
    </div>
  );
};

export default ListComponent;
