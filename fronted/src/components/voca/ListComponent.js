import { useParams } from "react-router-dom";

const ListComponent = () => {
  const { fid } = useParams();

  return (
    <div>
      <div>{fid}</div>
      <div>Voca List Component! </div>
    </div>
  );
};

export default ListComponent;
