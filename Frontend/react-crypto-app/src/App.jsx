import cryptoLogo from "./assets/logo.png";
import CryptoPrices from "./components/CryptoPrices.jsx";
import AccountBalance from "./components/AccountBalance.jsx";
import Holding from "./components/Holding.jsx";
import TransactionHistory from "./components/TransactionHistory.jsx";
import "./App.css";

function App() {
  return (
    <>
      <div className="logo react">
        <a target="_blank">
          <img src={cryptoLogo} className="logo react" alt="React logo" />
        </a>
      </div>

      <div className="App">
        <CryptoPrices />
      </div>
      <div className="App">
        <AccountBalance />
      </div>
      <div className="App">
        <Holding />
      </div>
      <div className="App">
        <TransactionHistory />
      </div>
    </>
  );
}

export default App;
