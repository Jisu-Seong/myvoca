const FooterMenu = () => {
  return (
    <div>
      <ul class="flex  text-black font-bold justify-center items-center space-x-8">
        <li>
          <div className="w-1/5 bg-grey-500 text-center">
            <div>SET</div>
          </div>
        </li>
        <li>
          <div className="w-1/5 bg-grey-500 text-center">
            <div>MYPAGE</div>
          </div>
        </li>
        <li>
          <div className="w-1/5 bg-grey-500 text-center">
            <div>VOCA</div>
          </div>
        </li>
        <li>
          <div className="w-1/5 bg-grey-500 text-center">
            <div>FOLDER</div>
          </div>
        </li>
        <li>
          <div className="w-1/5 bg-grey-500 text-center">
            <div>HELP</div>
          </div>
        </li>
      </ul>
    </div>
  );
};

export default FooterMenu;
