import { Suspense, lazy } from "react";
import { createBrowserRouter } from "react-router-dom";

const Loading = <div>Loading...</div>;
const Voca = lazy(() => import("../pages/VocaPage"));

const root = createBrowserRouter([
  {
    path: "",
    element: (
      <Suspense fallback={Loading}>
        <Voca />
      </Suspense>
    ),
  },
]);

export default root;
