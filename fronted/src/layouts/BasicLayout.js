import BasicMenu from "../components/menus/BasicMenu";

const BasicLayout = ({ children }) => {
  return (
    <>
      <BasicMenu />
      <div className=" w-full flex justify-center md:space-y-0">
        <main className="bg-teal-400 w-full lg:max-w-screen-md py-40">
          {children}
        </main>
      </div>
    </>
  );
};

export default BasicLayout;
