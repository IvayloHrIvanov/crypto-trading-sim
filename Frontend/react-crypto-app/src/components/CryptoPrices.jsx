import { useEffect, useState } from "react";
import { getCryptoPrices } from "@/api/CryptoPricesRequests.jsx";
import { formatCryptoPrices, updateCryptoPrices } from "@/util/cryptoUtils";
import CryptoDisplay from "@/components/CryptoDisplay";

function CryptoPrices() {
  const [cryptoPrices, setCryptoPrices] = useState([]);
  const [inputQuantity, setInputQuantity] = useState({});

  useEffect(() => {
    const fetchPrices = async () => {
      try {
        const rawPrices = await getCryptoPrices(); // Get Crypto prices
        const formattedPrices = formatCryptoPrices(rawPrices); // Filter and map the data for easier consumption

        setCryptoPrices((prevPrices) =>
          updateCryptoPrices(prevPrices, formattedPrices)
        );
      } catch (error) {
        console.error("Error fetching crypto prices:", error);
      }
    };

    fetchPrices();

    const interval = setInterval(fetchPrices, 1000); // Interval to Get Crypto prices every 1000ms
    return () => clearInterval(interval);
  }, []);

  const handleInputChange = (symbol, value) => { // Update the input field for every cryptocurrency
    setInputQuantity((prevValues) => {
      const updatedValues = Object.assign({}, prevValues);
      updatedValues[symbol] = value; // Update the specific symbol with the new value
      return updatedValues;
    });
  };

  return (
    <div className="crypto prices">
      <h1>Top 20 Cryptocurrency Pair Prices</h1>
              <CryptoDisplay 
              onInputChange = {handleInputChange}
              cryptoPrices = {cryptoPrices}
              inputQuantity = {inputQuantity}
              />
    </div>
  );
};

export default CryptoPrices;