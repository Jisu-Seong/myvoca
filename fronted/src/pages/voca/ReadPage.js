import ReadComponent from "../../components/voca/ReadComponent";
import { useParams } from "react-router-dom";

const ReadPage = () => {
  const { fid } = useParams();
  const { vid } = useParams();
  return (
    <div>
      <div>Voca Read Page!</div>
      <div>
        <ReadComponent vid={vid} fid={fid} />
      </div>
    </div>
  );
};

export default ReadPage;
