import { deleteAllAccountBalances, createAccountWithDefaultBalance } from "@/api/AccountBalanceRequests";
import { deleteAllHoldings } from "@/api/HoldingRequests";
import { deleteAllTransactions } from "@/api/TransactionRequests";

// Function to handle the process of reseting the simulation
export const HandleReset = async () => {
    //Delete all holdings, transactions and accounts
    await deleteAllHoldings();
    await deleteAllTransactions();
    await deleteAllAccountBalances();

    //Create account balance with default value
    await createAccountWithDefaultBalance();
    window.location.reload();
}