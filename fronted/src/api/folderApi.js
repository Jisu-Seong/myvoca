import jwtAxios from "../util/jwtUtil";
import { API_SERVER_HOST } from "./todoApi";

const prefix = `${API_SERVER_HOST}/api/folder`;

export const getOne = async (fid) => {
  const res = await jwtAxios.get(`${prefix}/${fid}`);
  return res.data;
};

export const getList = async () => {
  const res = await jwtAxios.get(`${prefix}/all`);
  return res.data;
};

export const postAdd = async (folderObj) => {
  const res = await jwtAxios.post(`${prefix}/`, folderObj);
  return res.data;
};

export const deleteOne = async (fid) => {
  const res = await jwtAxios.delete(`${prefix}/${fid}`);
  return res.data;
};

export const putOne = async (folder) => {
  const res = await jwtAxios.put(`${prefix}/${folder.fid}`, folder);
  return res.data;
};
