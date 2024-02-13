import { Link } from "react-router-dom";
import BasicLayout from "../layouts/BasicLayout";
import useCustomLogin from "./../hooks/useCustomLogin";

const MainPage = () => {
  const { isLogin, moveToLoginReturn } = useCustomLogin();

  if (!isLogin) {
    return moveToLoginReturn();
  }
  return (
    <BasicLayout>
      <div className=" text-3xl">Main Page</div>
    </BasicLayout>
  );
};

export default MainPage;
