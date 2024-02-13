import { Suspense, lazy } from "react";
import { Navigate } from "react-router-dom";

const Loading = <div>Loading...</div>;
const ListPage = lazy(() => import("../pages/folder/ListPage"));

const folderRouter = () => {
  return [
    {
      path: "list",
      element: (
        <Suspense fallback={Loading}>
          <ListPage />
        </Suspense>
      ),
    },
    // {
    //   path: "",
    //   element: <Navigate replace to="/folder/list/" />,
    // },
    // {
    //   path: "add",
    //   element: <Suspense fallback={Loading}></Suspense>,
    // },
    // {
    //   path: "read/:fid",
    //   element: <Suspense fallback={Loading}></Suspense>,
    // },
    // {
    //   path: "modify/:fid",
    //   element: <Suspense fallback={Loading}></Suspense>,
    // },
  ];
};

export default folderRouter;