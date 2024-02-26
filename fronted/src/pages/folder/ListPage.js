import { Link } from "react-router-dom";
import BasicLayout from "../../layouts/BasicLayout";
import useCustomLogin from "../../hooks/useCustomLogin";
import ListComponent from "../../components/folder/ListComponent";

const ListPage = () => {
  const { isLogin, moveToLoginReturn } = useCustomLogin();

  if (!isLogin) {
    return moveToLoginReturn();
  }
  return (
    <div>
      <div className="mx-10 text-3xl">Folder List</div>
      <ListComponent />
    </div>
  );
};

export default ListPage;
