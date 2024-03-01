import jwtAxios from "../util/jwtUtil";
import { API_SERVER_HOST } from "./todoApi";

const prefix = `${API_SERVER_HOST}/api/voca`;

export const getOne = async (vid) => {
  const res = await jwtAxios.get(`${prefix}/get/${vid}`);
  return res.data;
};

export const getList = async (foldername) => {
  const res = await jwtAxios.get(`${prefix}/list/${foldername}`);
  return res;
};

export const getListAll = async () => {
  const res = await jwtAxios.get(`${prefix}/listall`);
  return res;
};

export const postAdd = async (foldername, voca) => {
  const res = await jwtAxios.post(`${prefix}/add/${foldername}`, voca);
  return res.data;
};

export const deleteOne = async (vid) => {
  const res = await jwtAxios.delete(`${prefix}/delete/${vid}`);
  return res.data;
};

export const putOne = async (voca) => {
  const res = await jwtAxios.put(`${prefix}/modify/${voca.vid}`, voca);
  return res.data;
};
