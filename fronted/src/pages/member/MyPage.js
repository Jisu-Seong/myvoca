import { Link } from "react-router-dom";
import BasicLayout from "../../layouts/BasicLayout";
import useCustomLogin from "../../hooks/useCustomLogin";

const MyPage = () => {
  const { isLogin, moveToLoginReturn } = useCustomLogin();

  if (!isLogin) {
    return moveToLoginReturn();
  }
  return (
    <BasicLayout>
      <div className=" text-3xl">MyPage</div>
    </BasicLayout>
  );
};

export default MyPage;
