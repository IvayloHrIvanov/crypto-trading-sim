import { useEffect, useState } from "react";

import { getTransactions } from "@/api/TransactionRequests";

function TransactionHistory() {
  const [transactions, setTransactions] = useState([]);

  useEffect(() => {
    getTransactions()
      .then((response) => {
        setTransactions(response);
      })
      .catch((error) => {
        console.error("Error fetching transactions:", error);
      });
  }, []);

  return (
    <div>
      <h1>Transaction History</h1>
      <table>
        <tbody>
          {transactions.map((transaction, index) => (
            <tr key={index}>
              <td className="indexColumn">{index + 1 + "."}</td>
              <td className="transactionColumn">Type: {transaction.transactionType}</td>
              <td className="transactionColumn">Symbol: {transaction.assetSymbol}</td>
              <td className="transactionColumn">Quantity: {transaction.assetQuantity}</td>
              <td className="transactionColumn">Price: {transaction.assetPrice.toFixed(2)}</td>
              {transaction.profit != null && transaction.profit != undefined && (
              <td className="transactionColumn">Profit or Loss: {transaction.profit.toFixed(2)}</td>)}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default TransactionHistory;