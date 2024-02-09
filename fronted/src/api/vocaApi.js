import axios from "axios";

export const API_SERVER_HOST = "http://localhost:8080";

const prefix = `{API_SERVER_HOST}/api/voca`;

export const getOne = async (vno) => {
  const res = await jwtAxios.get(`${prefix}/${vno}`);
  return res.data;
};

export const getList = async (fno) => {
  const { page, size } = pageParam;
  const res = await jwtAxios.get(`${prefix}/list/{fno}`);
  return res.data;
};

export const postAdd = async (fid, vocaObj) => {
  const res = await jwtAxios.post(`${prefix}/add/${fid}`, vocaObj);
  return res.data;
};

export const deleteOne = async (vid) => {
  const res = await jwtAxios.delete(`${prefix}/${vid}`);
  return res.data;
};

export const putOne = async (voca) => {
  const res = await jwtAxios.put(`${prefix}/${voca.vid}/modify`, voca);
  return res.data;
};
