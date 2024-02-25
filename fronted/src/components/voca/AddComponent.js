import { useState } from "react";
import ResultModal from "../common/ResultModal";
import useCustomMove from "./../../hooks/useCustomMove";
import { postAdd } from "../../api/vocaApi";
import { styled } from "styled-components";
import { useParams } from "react-router-dom";

const initState = {
  vocaname: "",
  meaning: "",
  sentence: "",
};

const vocaFormDTO = {
  vocaRequestDTO: {
    vocaname: "",
    meaning: "",
    sentence: "",
  },
  tags: [],
};

const AddComponent = () => {
  const [voca, setVoca] = useState({ ...initState });
  const [result, setResult] = useState(null);
  const { moveToList } = useCustomMove();
  const { fid } = useParams();

  const [tagItem, setTagItem] = useState("");
  const [tagList, setTagList] = useState([]);

  const onKeyPress = (e) => {
    if (e.target.value.length !== 0 && e.key === "Enter") {
      submitTagItem();
    }
  };

  const submitTagItem = () => {
    let updatedTagList = [...tagList];
    updatedTagList.push(tagItem);
    setTagList(updatedTagList);
    setTagItem("");
  };

  const deleteTagItem = (e) => {
    const deleteTagItem = e.target.parentElement.firstChild.innerText;
    const filteredTagList = tagList.filter(
      (tagItem) => tagItem !== deleteTagItem
    );
    setTagList(filteredTagList);
  };

  const handleChangeFolder = (e) => {
    voca[e.target.name] = e.target.value;
    setVoca({ ...voca });
  };

  const handleClickAdd = () => {
    vocaFormDTO.vocaRequestDTO = voca;
    vocaFormDTO.tags = tagList;

    console.log(vocaFormDTO);

    postAdd(fid, vocaFormDTO)
      .then((result) => {
        console.log(result);
        setResult(result.vocaResponseDTO.vid);
        setVoca({ ...initState });
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
          <div className="w-1/5 p-6 text-right font-bold">folder</div>
          <div className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md">
            {fid}
          </div>
        </div>
      </div>

      <div className="flex justify-center">
        <div className="relative mb-4 flex w-full flex-wrap items-stretch">
          <div className="w-1/5 p-6 text-right font-bold">vocaname</div>
          <input
            className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md"
            name="vocaname"
            type={"text"}
            value={voca.vocaname}
            onChange={handleChangeFolder}
          />
        </div>
      </div>

      <div className="flex justify-center">
        <div className="relative mb-4 flex w-full flex-wrap items-stretch">
          <div className="w-1/5 p-6 text-right font-bold">meaning</div>
          <input
            className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md"
            name="meaning"
            type={"text"}
            value={voca.meaning}
            onChange={handleChangeFolder}
          />
        </div>
      </div>

      <div className="flex justify-center">
        <div className="relative mb-4 flex w-full flex-wrap items-stretch">
          <div className="w-1/5 p-6 text-right font-bold">sentence</div>
          <input
            className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md"
            name="sentence"
            type={"text"}
            value={voca.sentence}
            onChange={handleChangeFolder}
          />
        </div>
      </div>

      <div className="flex justify-center">
        <div className="relative mb-4 flex w-full flex-wrap items-stretch">
          <div className="w-1/5 p-6 text-right font-bold">tags</div>

          <TagBox>
            {tagList.map((tagItem, index) => {
              return (
                <TagItem key={index}>
                  <Text>{tagItem}</Text>
                  <Button onClick={deleteTagItem}>X</Button>
                </TagItem>
              );
            })}
            <TagInput
              type="text"
              placeholder="Press enter to add tags"
              tabIndex={2}
              onChange={(e) => setTagItem(e.target.value)}
              value={tagItem}
              onKeyPress={onKeyPress}
            />
          </TagBox>
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

const TagBox = styled.div`
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  min-height: 50px;
  margin: 10px;
  padding: 0 10px;
  border: 1px solid rgba(0, 0, 0, 0.3);
  &:focus-within {
    border-color: tomato;
  }
`;

const TagItem = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 5px;
  padding: 5px;
  background-color: tomato;
  border-radius: 5px;
  color: white;
  font-size: 13px;
`;

const Text = styled.span``;

const Button = styled.button`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 15px;
  height: 15px;
  margin-left: 5px;
  background-color: white;
  border-radius: 50%;
  color: tomato;
`;

const TagInput = styled.input`
  display: inline-flex;
  min-width: 150px;
  background: transparent;
  border: none;
  outline: none;
  cursor: text;
`;

export default AddComponent;
