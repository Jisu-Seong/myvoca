import BasicMenu from "../components/menus/BasicMenu";

const BasicLayout = ({ children }) => {
  return (
    <>
      <BasicMenu />
      <div className="bg-white my-5 w-full flex justify-center space-y-4 md:space-y-0">
        <main className="bg-sky-300 w-full lg:max-w-screen-md py-40">
          {children}
        </main>
      </div>
    </>
  );
};

export default BasicLayout;
