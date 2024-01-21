import BasicLayout from "../layouts/BasicLayout";

const VocaPage = () => {
  return (
    <BasicLayout>
      <div className=" text-3xl">
        <div className="bg-white text-left my-4 rounded-lg">
          <div className="ml-4 font-bold">folder 1</div>
        </div>
        <div className="bg-white rounded-lg py-3">
          <div className="flex-row flex mx-4 justify-between">
            <div className="flex-1">
              <div className="text-left">voca</div>
            </div>
            <div className="flex-1">
              <div className="text-left">[N]보카</div>
            </div>
          </div>
        </div>
      </div>
    </BasicLayout>
  );
};

export default VocaPage;
