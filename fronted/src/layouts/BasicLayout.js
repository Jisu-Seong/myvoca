import BasicMenu from "../components/menus/BasicMenu";
import FooterMenu from "../components/menus/FooterMenu";

const BasicLayout = ({ children }) => {
  return (
    <div className=" bg-white sm:w-[600px] rounded-lg border-4 border-gray-800 flex flex-col">
      <header class="bg-sky-300 rounded-lg shadow m-4 dark:bg-gray-800 h-100px">
        <div class="w-full mx-auto max-w-screen-xl p-4 flex justify-center items-center">
          <BasicMenu />
        </div>
      </header>

      <div className="bg-sky-300 rounded-lg shadow mx-4 dark:bg-gray-800 flex-1 justify-center overflow-auto">
        <main className=" px-5 rounded-md text-center">{children}</main>
      </div>
      <footer class="bg-sky-300 rounded-lg shadow m-4 dark:bg-gray-800 h-100px">
        <div class="w-full mx-auto max-w-screen-xl p-4 flex justify-center items-center">
          <FooterMenu />
        </div>
      </footer>
    </div>
  );
};

export default BasicLayout;
