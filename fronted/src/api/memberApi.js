import axios from "axios";
import { API_SERVER_HOST } from "./vocaApi";

const host = `${API_SERVER_HOST}/api/auth`;

export const loginPost = async (loginParam) => {
  const header = { headers: { "Content-Type": "x-www-form-urlencoded" } };

  const form = new FormData();
  form.append("email", loginParam.email);
  form.append("password", loginParam.password);

  const res = await axios.post(`${host}/login`, form, header);

  return res.data;
};
