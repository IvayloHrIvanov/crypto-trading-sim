import { useEffect, useState } from "react";
import HandleSell from "@/functions/HandleSell";

import { getHoldings } from "@/api/HoldingRequests";

function Holding() {
  const [holdings, setHoldings] = useState([]);
  const [inputValues, setInputValues] = useState({});

  useEffect(() => {
    getHoldings()
      .then((response) => {
        setHoldings(response);
      })
      .catch((error) => {
        console.error("Error fetching holdings: ", error);
      });
  }, []);

  const handleInputChange = (symbol, quantity, value) => {
    if (value <= quantity) {
      setInputValues((prevValues) => {
        const updatedValues = Object.assign({}, prevValues);
        updatedValues[symbol] = value; // Update the specific symbol with the new value
        return updatedValues; // Return the updated state
      });
    }
  };

  return (
    <div>
      <h1>Holdings</h1>
      <table>
        <tbody>
          {holdings.map((holdingItem, index) => (
            <tr key={index}>
              <td className="indexColumn">{index + 1 + "."}</td>
              <td>Symbol: {holdingItem.symbol},</td>
              <td>Quantity: {holdingItem.quantity},</td>
              <td>Price: ${holdingItem.price.toFixed(2)}</td>
              <td>
                <input
                  className="input"
                  type="number"
                  min="0"
                  value={inputValues[holdingItem.symbol] || 0}
                  onChange={(e) =>
                    handleInputChange(holdingItem.symbol, holdingItem.quantity, e.target.value)
                  }
                ></input>
              </td>
              <td>
                <div className="sellButton">
                  <button
                    onClick={() =>
                      HandleSell(
                        holdingItem,
                        holdingItem.symbol,
                        inputValues[holdingItem.symbol],
                        holdingItem.price.toFixed(2)
                      )}>Sell</button>
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Holding;