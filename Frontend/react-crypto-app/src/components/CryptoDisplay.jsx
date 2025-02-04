import React from "react";
import HandleBuy from "@/functions/HandleBuy";

const CryptoDisplay = ({onInputChange, cryptoPrices, inputQuantity}) => {
  return (
    <table className="crypto prices">
      <thead>
        <tr>
          <th className="header">№</th>
          <th className="header">Symbol</th>
          <th className="header">Price (USD)</th>
          <th className="headerBuyAmount">Buy Amount</th>
        </tr>
      </thead>
      <tbody>
        {cryptoPrices
          .slice()
          .sort((a, b) => b.price - a.price) // Sort the cryptocurrencies in descending order based on price
          .map((crypto, index) => ( // Display crypto prices
            <tr key={crypto.symbol}>
              <td className="indexColumn">{index + 1 + "."}</td>
              <td>{crypto.symbol ? crypto.symbol : "N/A"}</td>
              <td>
                {crypto.price ? `${crypto.price.toFixed(2)}$` : "N/A"}
              </td>
              <td>
                <input
                  className="input"
                  type="number"
                  min="0"
                  value={inputQuantity[crypto.symbol] || 0}
                  onChange={(e) => onInputChange(crypto.symbol, e.target.value)}
                ></input>
              </td>
              <td>
                <div className="buyButton">
                  <button onClick={() => HandleBuy(
                        crypto.symbol,
                        inputQuantity[crypto.symbol],
                        crypto.price.toFixed(2)
                      )}> BUY </button>
                </div>
              </td>
            </tr>
          ))}
      </tbody>
    </table>
  );
};

export default CryptoDisplay;