import { Suspense, lazy } from "react";
import { createBrowserRouter } from "react-router-dom";

const Loading = <div>Loading...</div>;
const Voca = lazy(() => import("../pages/VocaPage"));
const Folder = lazy(() => import("../pages/FolderPage"));
const Member = lazy(() => import("../pages/MemberPage"));
const Setting = lazy(() => import("../pages/SettingPage"));
const AddVoca = lazy(() => import("../pages/AddVocaPage"));
const Sort = lazy(() => import("../pages/SortPage"));

const root = createBrowserRouter([
  {
    path: "",
    element: (
      <Suspense fallback={Loading}>
        <Voca />
      </Suspense>
    ),
  },
  {
    path: "/folder",
    element: (
      <Suspense fallback={Loading}>
        <Folder />
      </Suspense>
    ),
  },
  {
    path: "/member",
    element: (
      <Suspense fallback={Loading}>
        <Member />
      </Suspense>
    ),
  },
  {
    path: "/setting",
    element: (
      <Suspense fallback={Loading}>
        <Setting />
      </Suspense>
    ),
  },
  {
    path: "/addvoca",
    element: (
      <Suspense fallback={Loading}>
        <AddVoca />
      </Suspense>
    ),
  },
  {
    path: "/sort",
    element: (
      <Suspense fallback={Loading}>
        <Sort />
      </Suspense>
    ),
  },
]);

export default root;
