import React, { useEffect, useState } from "react";
import { getCryptoPrices } from "@/api/Api.jsx";
import { formatCryptoPrices, updateCryptoPrices } from "@/util/cryptoUtils";
import CryptoRow from "@/components/CryptoRow";

const CryptoPrices = () => {
  const [cryptoPrices, setCryptoPrices] = useState([]);
  const [buyCounts, setBuyCounts] = useState({}); // State to track number of coins bought for each symbol
  const [inputValues, setInputValues] = useState({}); // State to track the input values

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
    setInputValues((prevValues) => {
      const updatedValues = Object.assign({}, prevValues);
      updatedValues[symbol] = value; // Update the specific symbol with the new value
      return updatedValues; // Return the updated state
    });
  };

  const handleBuy = (symbol) => {
    const amountToBuy = parseFloat(inputValues[symbol] || 0); // Get the amount to buy for this symbol
    if (amountToBuy > 0) {
      setBuyCounts((prevCounts) => {
        const updatedCounts = Object.assign({}, prevCounts);
        updatedCounts[symbol] = (updatedCounts[symbol] || 0) + amountToBuy; // Add to the count for this symbol
        return updatedCounts; // Return the updated state
      });
      setInputValues((prevValues) => {
        const updatedValues = Object.assign({}, prevValues);
        updatedValues[symbol] = 0; // Reset the input value for this symbol
        return updatedValues; // Return the updated state
      });
    }
  };

  return (
    <div>
      <h1>Top 20 Cryptocurrency Pair Prices</h1>
      <table className="">
        <thead>
          <tr>
            <th className="header">№</th>
            <th className="header">Symbol</th>
            <th className="header">Price (USD)</th>
            <th className="header">Buy Amount</th>
          </tr>
        </thead>
        <tbody>
          {cryptoPrices
            .slice()
            .sort((a, b) => b.price - a.price)
            .map((crypto, index) => (
              <CryptoRow
                key={crypto.symbol}
                index={index}
                crypto={crypto}
                inputValue={inputValues[crypto.symbol]}
                buyCount={buyCounts[crypto.symbol]}
                onInputChange={handleInputChange}
                onBuy={handleBuy}
              />
            ))}
        </tbody>
      </table>
    </div>
  );
};

export default CryptoPrices;