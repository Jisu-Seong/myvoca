import { Suspense, lazy } from "react";

const Loading = <div>Loading...</div>;
const Login = lazy(() => import("../pages/member/LoginPage"));
const Logout = lazy(() => import("../pages/member/LogoutPage"));
const MemberModify = lazy(() => import("../pages/member/ModifyPage"));
const Join = lazy(() => import("../pages/member/JoinPage"));
const MyPage = lazy(() => import("../pages/member/MyPage"));

const memberRouter = () => {
  return [
    {
      path: "join",
      element: (
        <Suspense fallback={Loading}>
          <Join />
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
      path: "logout",
      element: (
        <Suspense fallback={Loading}>
          <Logout />
        </Suspense>
      ),
    },
    {
      path: "modify",
      element: (
        <Suspense fallback={Loading}>
          <MemberModify />
        </Suspense>
      ),
    },
    {
      path: "mypage",
      element: (
        <Suspense fallback={Loading}>
          <MyPage />
        </Suspense>
      ),
    },
  ];
};

export default memberRouter;
