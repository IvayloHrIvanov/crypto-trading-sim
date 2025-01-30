import { useEffect, useState } from "react";
import { getCryptoPrices } from "@/api/CryptoPricesRequests.jsx";
import { formatCryptoPrices, updateCryptoPrices } from "@/util/cryptoUtils";
import CryptoDisplay from "@/components/CryptoDisplay";

function CryptoPrices() {
  const [cryptoPrices, setCryptoPrices] = useState([]);
  const [inputQuantity, setinputQuantity] = useState({}); // State to track the input values

  useEffect(() => {
    const fetchPrices = async () => {
      try {
        const rawPrices = await getCryptoPrices();
        // Filter the ticker updates and map the data for easier consumption
        const formattedPrices = formatCryptoPrices(rawPrices);

        setCryptoPrices((prevPrices) =>
          updateCryptoPrices(prevPrices, formattedPrices)
        );
      } catch (error) {
        console.error("Error fetching crypto prices:", error);
      }
    };

    fetchPrices();

    const interval = setInterval(fetchPrices, 1000);
    return () => clearInterval(interval);
  }, []);

  const handleInputChange = (symbol, value) => {
    setinputQuantity((prevValues) => {
      const updatedValues = Object.assign({}, prevValues);
      updatedValues[symbol] = value; // Update the specific symbol with the new value
      return updatedValues; // Return the updated state
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