import { Link } from "react-router-dom";
import { useSelector } from "react-redux";

const BasicMenu = () => {
  const loginState = useSelector((state) => state.loginSlice);

  return (
    <nav id="navbar" className=" flex bg-blue-300">
      <div className="w-4/5 bg-gray-500">
        <ul className="flex p-4 text-whit font-bold">
          <li className="pr-6 text-2xl">
            <Link to={"/"}>MYVOCA</Link>
          </li>
          <li className="pr-6 text-2xl">
            <Link to={"/folder/list"}>folder</Link>
          </li>
          <li className="pr-6 text-2xl">
            <Link to={"/voca/list"}>voca</Link>
          </li>
          <li className="pr-6 text-2xl">
            <Link to={"/member/mypage/"}>mypage</Link>
          </li>
        </ul>
      </div>

      <div className="w-1/5 flex justify-end bg-orange-300 p-4 fomt-medium">
        {!loginState.email ? (
          <div>
            <div className="text-white text-sm m-1 rounded">
              <Link to={"/member/login"}>Login</Link>
            </div>
          </div>
        ) : (
          <div className="text-white text-sm m-1 rounded">
            <Link to={"/member/logout"}>Logout</Link>
          </div>
        )}
      </div>
    </nav>
  );
};

export default BasicMenu;
