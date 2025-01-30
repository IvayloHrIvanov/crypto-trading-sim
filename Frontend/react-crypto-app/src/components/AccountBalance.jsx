import { useEffect, useState } from "react";

import { getAccountBalances } from "@/api/AccountBalanceRequests";

function AccountBalance() {
  const [balances, setBalances] = useState([]);

  useEffect(() => {
    getAccountBalances()
      .then((response) => { //Get all Account Balances
        setBalances(response);
      })
      .catch((error) => {
        console.error("Error fetching account balances:", error);
      });
  }, []);

  return (
    <div id="content " className="ribbon">
      <h2>Account Balance</h2>
      <div>
        {balances.map((accountBalance, index) => ( //Display Account Balances
          <div key={index}>
            <p>Balance: {accountBalance.accountBalance.toFixed(2)}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default AccountBalance;