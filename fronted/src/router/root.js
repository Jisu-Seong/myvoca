import { Suspense, lazy } from "react";
import memberRouter from "./memberRouter";
import folderRouter from "./folderRouter";
import vocaRouter from "./vocaRouter";

const { createBrowserRouter } = require("react-router-dom");

const Loading = <div>Loading...</div>;
const Main = lazy(() => import("../pages/MainPage"));
const About = lazy(() => import("../pages/AboutPage"));
const FolderIndex = lazy(() => import("../pages/folder/IndexPage"));
const VocaIndex = lazy(() => import("../pages/voca/IndexPage"));

const root = createBrowserRouter([
  {
    path: "",
    element: (
      <Suspense fallback={Loading}>
        <Main />
      </Suspense>
    ),
  },
  {
    path: "about",
    element: (
      <Suspense fallback={Loading}>
        <About />
      </Suspense>
    ),
  },
  {
    path: "folder",
    element: (
      <Suspense fallback={Loading}>
        <FolderIndex />
      </Suspense>
    ),
    children: folderRouter(),
  },
  {
    path: "voca",
    element: (
      <Suspense fallback={Loading}>
        <VocaIndex />
      </Suspense>
    ),
    children: vocaRouter(),
  },
  {
    path: "member",
    children: memberRouter(),
  },
]);

export default root;
