import { useParams } from "react-router-dom";
import ListComponent from "../../components/voca/ListComponent";

const ListPage = () => {
  const { foldername } = useParams();

  return (
    <div>
      <ListComponent foldername={foldername} />
    </div>
  );
};

export default ListPage;
