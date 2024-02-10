import { RouterProvider } from "react-router-dom";
import root from "./router/root";
import { IsLoginProvider } from "./contexts/IsLoginContext";

function App() {
  return (
    <>
      <IsLoginProvider>
        <RouterProvider router={root} />
      </IsLoginProvider>
    </>
  );
}

export default App;
