import { getAccountBalances, updateAccountBalance } from "@/api/AccountBalanceRequests";
import { deleteAllHoldings } from "@/api/HoldingRequests";
import { deleteAllTransactions } from "@/api/TransactionRequests";

export const HandleReset = async () => {
    await deleteAllHoldings();
    await deleteAllTransactions();

    const accountBalances = await getAccountBalances();

    await updateAccountBalance(accountBalances[0].accountId, 10000);
    window.location.reload();
}