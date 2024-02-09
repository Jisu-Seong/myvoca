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

  // folder list
  // folder 1 -> voca list
  // tag 1 -> voca list
  // voca add
  // voca modify
  // voca 1 -> voca details
};
