import {
  createSearchParams,
  useParams,
  useNavigate,
  useSearchParams,
} from "react-router-dom";
import { useCallback } from "react";
import ReadComponent from "../../components/folder/ReadComponent";

const ReadPage = () => {
  const { fid } = useParams();

  return (
    <div className="font-extrabold w-full bg-white mt-6">
      <div className="text-2xl">Folder Read Page Component {fid}</div>
      <div>
        <ReadComponent fid={fid} />
      </div>
    </div>
  );
};

export default ReadPage;
