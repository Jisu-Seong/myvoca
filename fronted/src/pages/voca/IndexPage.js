import { Outlet, useNavigate, useParams } from "react-router-dom";
import BasicLayout from "../../layouts/BasicLayout";
import { useCallback } from "react";
import useCustomLogin from "../../hooks/useCustomLogin";

const IndexPage = () => {
  const { fid } = useParams();
  const { foldername } = useParams();
  const { isLogin, moveToLoginReturn } = useCustomLogin();
  const navigate = useNavigate();

  const handleClickList = useCallback(() => {
    navigate({ pathname: `list/${foldername}` });
  });

  const handleClickAdd = useCallback(() => {
    navigate({ pathname: `add/${foldername}` });
  });

  if (!isLogin) {
    return moveToLoginReturn();
  }
  return (
    <BasicLayout>
      <div className="w-full flex">
        <div
          className="text-xl m-1 p-2 w-20 font-extrabold text-center underline"
          onClick={handleClickList}
        >
          LIST
        </div>
        <div
          className="text-xl m-1 p-2 w-20 font-extrabold text-center underline"
          onClick={handleClickAdd}
        >
          ADD
        </div>
      </div>
      <div className="flex flex-wrap w-full">
        <Outlet />
      </div>
    </BasicLayout>
  );
};

export default IndexPage;
