import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import "boxicons";

const BasicMenu = () => {
  const loginState = useSelector((state) => state.loginSlice);

  return (
    <nav id="navbar" className="bg-gray-500 w-full flex justify-center">
      <div className="flex w-full lg:max-w-screen-md justify-between ">
        <div className="flex justify-center">
          <ul className="flex p-4 text-whit font-bold">
            <li className="text-3xl">
              <Link to={"/"}>MYVOCA</Link>
            </li>
            <li className="pr-6 pl-12 text-2xl">
              <Link to={"/folder/list"}>
                <box-icon name="folder" size="md"></box-icon>
              </Link>
            </li>
            <li className="pr-6 text-2xl">
              <Link to={"/voca/list/all"}>
                <box-icon name="font-family" size="md"></box-icon>
              </Link>
            </li>
          </ul>
        </div>

        <div className="flex justify-end p-4 fomt-medium">
          <div className="pr-6 ">
            <Link to={"/member/mypage/"}>
              <box-icon type="solid" name="face" size="md"></box-icon>
            </Link>
          </div>
          {!loginState.email ? (
            <div>
              <div className="text-sm m-1 rounded">
                <Link to={"/member/login"}>Login</Link>
              </div>
            </div>
          ) : (
            <div className="text-xl m-1 rounded">
              <Link to={"/member/logout"}>Logout</Link>
            </div>
          )}
        </div>
      </div>
    </nav>
  );
};

export default BasicMenu;
