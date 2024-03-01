import {
  createSearchParams,
  useNavigate,
  useSearchParams,
} from "react-router-dom";
import { useState } from "react";

const getNum = (param, defaultValue) => {
  if (!param) {
    return defaultValue;
  }
  return parseInt(param);
};

const useCustomMove = () => {
  const navigate = useNavigate();
  const [refresh, setRefresh] = useState(false);
  const [queryParams] = useSearchParams();

  const moveToList = (pageParam) => {
    setRefresh(!refresh);

    navigate({ pathname: "../list" });
  };

  // const moveToModify = (num) => {
  //   navigate({
  //     pathname: `../modify/${num}`,
  //   });
  // };

  // const moveToRead = (num) => {
  //   navigate({
  //     pathname: `../read/${num}`,
  //   });
  // };

  const moveToVocaRead = (vid, fid) => {
    navigate({
      pathname: `/voca/${fid}/read/${vid}`,
    });
  };

  const moveToVocaList = (foldername) => {
    navigate({
      pathname: `/voca/list/${foldername}`,
    });
  };

  const moveToVocaModify = (vid, fid) => {
    navigate({
      pathname: `/voca/${fid}/modify/${vid}`,
    });
  };

  const moveToVocaListAll = () => {
    navigate({
      pathname: `/voca/list/all`,
    });
  };

  return {
    moveToList,
    moveToVocaRead,
    moveToVocaList,
    moveToVocaModify,
    refresh,
    moveToVocaListAll,
  };
};

export default useCustomMove;
