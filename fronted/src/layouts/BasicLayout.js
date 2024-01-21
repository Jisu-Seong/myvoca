import BasicMenu from "../components/menus/BasicMenu";
import FooterMenu from "../components/menus/FooterMenu";

const BasicLayout = ({ children }) => {
  return (
    <div className=" bg-white sm:w-[600px] rounded-lg border-4 border-gray-700 flex flex-col">
      <header class="bg-white rounded-lg shadow m-4 p-4 border-1 border-gray-500 dark:bg-gray-500 h-100px">
        <BasicMenu />
      </header>

      <div className="bg-green-300 rounded-lg shadow mx-4 border-1 border-gray-500 dark:bg-gray-500 flex-1 justify-center overflow-auto">
        <main className=" px-5 rounded-md text-center">{children}</main>
      </div>

      <footer class=" bg-white rounded-lg shadow m-4 border-1 border-gray-500 dark:bg-gray-500 h-100px justify-center items-center align-middle">
        <div class="w-full mx-auto max-w-screen-xl p-4 flex justify-center items-center align-middle">
          <FooterMenu />
        </div>
      </footer>
    </div>
  );
};

export default BasicLayout;
