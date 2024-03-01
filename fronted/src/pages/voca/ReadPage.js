import ReadComponent from "../../components/voca/ReadComponent";
import { useParams } from "react-router-dom";

const ReadPage = () => {
  const { vid } = useParams();
  const { foldername } = useParams();
  return (
    <div className="w-full flex justify-center">
      <ReadComponent vid={vid} foldername={foldername} />
    </div>
  );
};

export default ReadPage;
