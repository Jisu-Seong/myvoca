import ReadComponent from "../../components/voca/ReadComponent";
import { useParams } from "react-router-dom";

const ReadPage = () => {
  const { fid } = useParams();
  const { vid } = useParams();
  return (
    <div className="w-full flex justify-center">
      <ReadComponent vid={vid} fid={fid} />
    </div>
  );
};

export default ReadPage;
