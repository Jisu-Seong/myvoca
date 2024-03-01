import { Suspense, lazy } from "react";
import { Navigate } from "react-router-dom";

const Loading = <div>Loading...</div>;
const ListPage = lazy(() => import("../pages/voca/ListPage"));
const AddPage = lazy(() => import("../pages/voca/AddPage"));
const ReadPage = lazy(() => import("../pages/voca/ReadPage"));
const ModifyPage = lazy(() => import("../pages/voca/ModifyPage"));

const vocaRouter = () => {
  return [
    {
      path: "list/all",
      element: (
        <Suspense fallback={Loading}>
          <ListPage />
        </Suspense>
      ),
    },
    {
      path: "list/:foldername",
      element: (
        <Suspense fallback={Loading}>
          <ListPage />
        </Suspense>
      ),
    },
    {
      path: "",
      element: <Navigate replace to="list" />,
    },
    {
      path: "add/:fid",
      element: (
        <Suspense fallback={Loading}>
          <AddPage />
        </Suspense>
      ),
    },
    {
      path: ":fid/read/:vid",
      element: (
        <Suspense fallback={Loading}>
          <ReadPage />
        </Suspense>
      ),
    },
    {
      path: ":fid/modify/:vid",
      element: (
        <Suspense fallback={Loading}>
          <ModifyPage />
        </Suspense>
      ),
    },
  ];
};

export default vocaRouter;
