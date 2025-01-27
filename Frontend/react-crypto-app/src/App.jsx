import cryptoLogo from "./assets/logo.png";
import CryptoPrices from "./components/CryptoPrices.jsx";
import "./App.css";

function App() {
  return (
    <>
      <div>
        <a target="_blank">
          <img src={cryptoLogo} className="logo react" alt="React logo" />
        </a>
      </div>

      <div className="App">
        <CryptoPrices/>
      </div>
    </>
  );
}

export default App;