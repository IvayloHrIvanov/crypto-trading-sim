import React from "react";

const CryptoRow = ({
  index,
  crypto,
  inputValue,
  buyCount,
  onInputChange,
  onBuy,
}) => {
  return (
    <tr key={crypto.symbol}>
      <td className="cryptoNumeration">{index + 1 + "."}</td>
      <td className="">{crypto.symbol ? crypto.symbol : "N/A"}</td>
      <td className="">
        {crypto.price ? `${crypto.price.toFixed(2)}$` : "N/A"}
      </td>
      <td className="buyCount">
        <input
          className="inputBuyCount"
          type="number"
          min="0"
          value={inputValue || 0}
          onChange={(e) => onInputChange(crypto.symbol, e.target.value)}
        ></input>
      </td>
      <td className="">
        <div className="buyButton">
          <button onClick={() => onBuy(crypto.symbol)}> BUY</button>
        </div>
      </td>
      <td className="boughtNumeration">You have: {buyCount || 0}</td>
    </tr>
  );
};

export default CryptoRow;