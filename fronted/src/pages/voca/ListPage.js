import { useParams } from "react-router-dom";
import ListComponent from "../../components/voca/ListComponent";

const ListPage = () => {
  const { fid } = useParams();

  return (
    <div>
      <div>Voca List Page! </div>
      <ListComponent fid={fid} />
    </div>
  );
};

export default ListPage;
