import { useState } from "react";
import ResultModal from "../common/ResultModal";
import useCustomMove from "./../../hooks/useCustomMove";
import { postAdd } from "../../api/folderApi";

const initState = {
  foldername: "",
};

const AddComponent = () => {
  const [folder, setFolder] = useState({ ...initState });
  const [result, setResult] = useState(null);
  const { moveToList } = useCustomMove();

  const handleChangeFolder = (e) => {
    folder[e.target.name] = e.target.value;
    setFolder({ ...folder });
  };

  const handleClickAdd = () => {
    console.log(folder);
    postAdd(folder)
      .then((result) => {
        console.log(result);
        setResult(result.fid);
        setFolder({ ...initState });
      })
      .catch((e) => {
        console.error(e);
      });
  };

  const closeModal = () => {
    setResult(null);
    moveToList();
  };

  return (
    <div className="border-2 border-sky-200 mt-10 m-2 p-4">
      {result ? (
        <ResultModal
          title={"Add Result"}
          content={`New ${result} Added`}
          callbackFn={closeModal}
        />
      ) : (
        <></>
      )}

      <div className="flex justify-center">
        <div className="relative mb-4 flex w-full flex-wrap items-stretch">
          <div className="w-1/5 p-6 text-right font-bold">folder name</div>
          <input
            className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md"
            name="foldername"
            type={"text"}
            value={folder.foldername}
            onChange={handleChangeFolder}
          />
        </div>
      </div>

      <div className="flex justify-end">
        <div className="relative mb-4 flex p-4 flex-wrap items-stretch">
          <button
            type="button"
            className="rounded p-4 w-36 bg-blue-500 text-xl text-white "
            onClick={handleClickAdd}
          >
            ADD
          </button>
        </div>
      </div>
    </div>
  );
};

export default AddComponent;
