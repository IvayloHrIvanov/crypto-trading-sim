import { useEffect, useState } from "react";
import HandleSell from "@/functions/HandleSell";

import { getHoldings } from "@/api/HoldingRequests";

function Holding() {
  const [holdings, setHoldings] = useState([]);
  const [inputValues, setInputValues] = useState({});

  useEffect(() => {
    getHoldings() // Get all Holdings
      .then((response) => {
        setHoldings(response);
      })
      .catch((error) => {
        console.error("Error fetching holdings: ", error);
      });
  }, []);

  const handleInputChange = (index, quantity, value) => {
    if (value <= quantity) { // Ensure that the value to sell doesn't exceed the available quantity
      const updatedValues = Object.assign({}, inputValues);
      updatedValues[index] = value; // Update the input value only for the specific index
      setInputValues(updatedValues);
    }
  };

  return (
    <div>
      <h1>Holdings</h1>
      <table>
        <tbody>
          {holdings
          .slice()
          .sort((a, b) => a.symbol.localeCompare(b.symbol)) // Sort holdings based on symbol
          .map((holdingItem, index) => ( // Display Holdings
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
                  value={inputValues[index] || 0}
                  onChange={(e) =>
                    handleInputChange(index, holdingItem.quantity, e.target.value)
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
                        inputValues[index],
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