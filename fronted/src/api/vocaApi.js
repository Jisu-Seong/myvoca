import jwtAxios from "../util/jwtUtil";
import { API_SERVER_HOST } from "./todoApi";

const prefix = `${API_SERVER_HOST}/api/voca`;

export const getOne = async (vid) => {
  const res = await jwtAxios.get(`${prefix}/${vid}`);
  return res.data;
};

export const getList = async (fid) => {
  const res = await jwtAxios.get(`${prefix}/list/{fid}`);
  return res.data;
};

export const postAdd = async (fid, voca) => {
  const res = await jwtAxios.post(`${prefix}/add/${fid}`, voca);
  return res.data;
};

export const deleteOne = async (vid) => {
  const res = await jwtAxios.delete(`${prefix}/${vid}`);
  return res.data;
};

export const putOne = async (voca) => {
  const res = await jwtAxios.put(`${prefix}/modify/${voca.vid}`, voca);
  return res.data;
};
