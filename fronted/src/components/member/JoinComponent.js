import { useState } from "react";
import axios from "axios";
import { API_SERVER_HOST } from "../../api/todoApi";
import useCustomLogin from "../../hooks/useCustomLogin";

const host = `${API_SERVER_HOST}/member`;

const initState = {
  email: "",
  pw: "",
};
const JoinComponent = () => {
  const [joinParam, setJoinParam] = useState({ ...initState });
  const { doJoin, moveToPath } = useCustomLogin();

  const handleChange = (e) => {
    joinParam[e.target.name] = e.target.value;
    setJoinParam({ ...joinParam });
  };

  const handleClickJoin = (e) => {
    doJoin(joinParam).then((data) => {
      console.log(data);
      if (data === undefined) {
        alert("아이디와 비밀번호를 다시 확인해주세요.");
      } else {
        if (data.MESSAGE === "SUCCESS") {
          alert("가입되었습니다.");
          moveToPath("/");
        } else if (data.MESSAGE === "EXIST") {
          alert("이미 존재하는 이메일입니다.");
        } else {
          alert("아이디와 비밀번호를 다시 확인해주세요.");
        }
      }
    });
  };

  return (
    <div className="border-2 border-sky-200 mt-10 m-2 p-4">
      <div className="flex justify-center">
        <div className="text-4xl m-4 p-4 font-extrabold text-blue-500">
          JOIN US
        </div>
      </div>
      <div className="flex justify-center">
        <div className="relative mb-4 flex w-full flex-wrap items-stretch">
          <input
            className="w-full p-4 rounded-r border border-solid border-neutral-500 shadow-md"
            name="email"
            type={"text"}
            value={joinParam.email}
            onChange={handleChange}
            placeholder="Email"
          />
        </div>
      </div>
      <div className="flex justify-center">
        <div className="relative mb-4 flex w-full flex-wrap items-stretch">
          <input
            className="w-full p-4 rounded-r border border-solid border-neutral-500 shadow-md"
            name="pw"
            type={"password"}
            value={joinParam.pw}
            onChange={handleChange}
            placeholder="Password"
          />
        </div>
      </div>

      <div className="flex justify-center">
        <div className="relative mb-4 flex w-full justify-center">
          <div className="w-2/5 p-6 flex justify-center">
            <button
              className="rounded p-4 w-36 bg-blue-500 text-xl text-white"
              onClick={handleClickJoin}
            >
              JOIN
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default JoinComponent;
