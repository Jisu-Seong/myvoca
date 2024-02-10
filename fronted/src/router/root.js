import { Suspense, lazy } from "react";
import { createBrowserRouter, Navigate } from "react-router-dom";

const Loading = <div>Loading...</div>;
const Login = lazy(() => import("../pages/LoginPage"));
const MainPage = lazy(() => import("../pages/MainPage"));
const MemberPage = lazy(() => import("../pages/MemberPage"));

const root = createBrowserRouter([
  {
    path: "/",
    element: (
      <Suspense fallback={Loading}>
        <MainPage />
      </Suspense>
    ),
  },
  {
    path: "login",
    element: (
      <Suspense fallback={Loading}>
        <Login />
      </Suspense>
    ),
  },
  {
    path: "memberpage",
    element: (
      <Suspense fallback={Loading}>
        <MemberPage />
      </Suspense>
    ),
  },
]);

export default root;
