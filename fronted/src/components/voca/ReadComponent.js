import { useCallback, useEffect, useState } from "react";
import { getOne } from "../../api/vocaApi";
import { useNavigate, Navigate } from "react-router-dom";
import useCustomMove from "./../../hooks/useCustomMove";

const initState = {
  vid: 0,
  fid: 0,
  vocaname: 0,
  meaning: "",
  sentence: "",
  isMarked: false,
  createdAt: "",
  updatedAt: "",
  tags: [],
};

const ReadComponent = ({ vid }, { fid }) => {
  const navigate = useNavigate();
  const [voca, setVoca] = useState(initState);
  const { moveToVocaList, moveToVocaModify } = useCustomMove();

  useEffect(() => {
    getOne(vid).then((data) => {
      console.log(data);
      setVoca(data);
    });
  }, [vid]);

  return (
    <div className=" bg-white mt-10 m-2 p-4 rounded">
      {makeDiv("voca", voca.vocaname)}
      {makeDiv("meaning", voca.meaning)}
      <div className="flex justify-center">
        <div className="relative mb-4 flex w-full flex-wrap items-stretch">
          <div className="w-1/5 p-6 text-rignt font-bold">tags</div>
          {voca.tags.map((tag) => (
            <div className="w-1/5 p-6 rounded-r border border-solid shadow-md">
              <div>{tag}</div>
            </div>
          ))}
        </div>
      </div>
      {makeDiv("sentence", voca.sentence)}
      {makeDiv("createdAt", voca.createdAt)}
      {makeDiv("updatedAt", voca.updatedAt)}

      <div className="flex justify-end p-4">
        <button
          type="button"
          className="rounded p-4 m-2 text-xl w-32 text-white bg-orange-500"
          onClick={() => moveToVocaList(voca.fid)}
        >
          To Voca List
        </button>

        <button
          type="button"
          className="rounded p-4 m-2 text-xl w-32 text-white bg-red-500"
          onClick={() => moveToVocaModify(voca.vid, voca.fid)}
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
