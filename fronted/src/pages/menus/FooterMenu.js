import "boxicons";
import { Link } from "react-router-dom";

const FooterMenu = () => {
  return (
    <div class="flex text-grey-500 font-bold justify-between w-full align-middle">
      <div className="w-1/5 bg-grey-500 text-center align-middle">
        <Link to={"/setting"}>
          <box-icon name="cog" size="md"></box-icon>
        </Link>
      </div>
      <div className="w-1/5 bg-grey-500 text-center align-middle">
        <Link to={"/folder"}>
          <box-icon name="folder" size="md"></box-icon>
        </Link>
      </div>
      <div className="w-1/5 bg-grey-500 text-center align-middle">
        <Link to={"/"}>
          <box-icon name="home" size="md"></box-icon>
        </Link>
      </div>

      <div className="w-1/5 bg-grey-500 text-center align-middle">
        <Link to={"/addvoca"}>
          <box-icon name="plus-circle" size="md"></box-icon>
        </Link>
      </div>

      <div className="w-1/5 bg-grey-500 text-center align-middle">
        <Link to={"/sort"}>
          <box-icon name="sort" size="md"></box-icon>
        </Link>
      </div>
    </div>
  );
};

export default FooterMenu;
