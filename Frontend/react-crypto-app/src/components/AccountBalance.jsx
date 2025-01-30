import { useEffect, useState } from "react";

import { getAccountBalances } from "@/api/AccountBalanceRequests";

function AccountBalance() {
  const [balances, setBalances] = useState([]);

  useEffect(() => {
    getAccountBalances()
      .then((response) => {
        setBalances(response);
      })
      .catch((error) => {
        console.error("Error fetching account balances:", error);
      });
  }, []);

  return (
    <div id="content " className="ribbon">
      <h2 className="">Account Balance</h2>
      <div className="">
        {balances.map((accountBalance, index) => (
          <div key={index} className="">
            <p>Balance: {accountBalance.accountBalance.toFixed(2)}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default AccountBalance;