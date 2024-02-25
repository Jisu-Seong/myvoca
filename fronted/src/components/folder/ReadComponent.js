import { useCallback, useEffect, useState } from "react";
import { getOne } from "../../api/folderApi";
import useCustomMove from "../../hooks/useCustomMove";
import { useNavigate } from "react-router-dom";
import { Navigate } from "react-router-dom";

const initState = {
  fid: 0,
  foldername: "",
  createdAt: "",
  updatedAt: "",
};

const ReadComponent = ({ fid }) => {
  const navigate = useNavigate();
  const [folder, setFolder] = useState(initState);

  const { moveToList, moveToModify, moveToVocaList } = useCustomMove();

  const handleMoveVocaList = () => {
    moveToVocaList(fid);
  };

  useEffect(() => {
    getOne(fid).then((data) => {
      console.log(data);
      setFolder(data);
    });
  }, [fid]);

  return (
    <div className="border-2 border-sky-200 mt-10 m-2 p-4 ">
      {makeDiv("fid", folder.fid)}
      {makeDiv("foldername", folder.foldername)}
      {makeDiv("createdAt", folder.createdAt)}
      {makeDiv("updatedAt", folder.updatedAt)}

      <div className="flex justify-end p-4">
        <button
          type="button"
          className="rounded p-4 m-2 text-xl w-32 text-white bg-orange-500"
          onClick={() => handleMoveVocaList(fid)}
        >
          To Voca List
        </button>

        <button
          type="button"
          className="rounded p-4 m-2 text-xl w-32 text-white bg-blue-500"
          onClick={() => moveToList()}
        >
          Folder List
        </button>

        <button
          type="button"
          className="rounded p-4 m-2 text-xl w-32 text-white bg-red-500"
          onClick={() => moveToModify(fid)}
        >
          Modify
        </button>
      </div>
    </div>
  );
};

const makeDiv = (title, value) => (
  <div className="flex justify-center">
    <div className="relative mb-4 flex w-full flex-wrap items-stretch">
      <div className="w-1/5 p-6 text-rignt font-bold">{title}</div>
      <div className="w-4/5 p-6 rounded-r border border-solid shadow-md">
        {value}
      </div>
    </div>
  </div>
);
export default ReadComponent;
