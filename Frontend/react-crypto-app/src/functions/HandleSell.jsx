import {
  getAccountBalances,
  updateAccountBalance,
} from "@/api/AccountBalanceRequests";
import { updateHolding, deleteHolding } from "@/api/HoldingRequests";
import { createTransaction } from "@/api/TransactionRequests";
import { getCryptoPrices } from "@/api/CryptoPricesRequests";
import {
  buildSellTransactionData,
  createHoldingBody,
  buildSellTransactionWithoutHoldingData,
} from "@/util/RequestUtils";
import { formatCryptoPrices } from "@/util/cryptoUtils";

const HandleSell = async (holding, symbol, quantity, price) => {
  try {
    const accountBalances = await getAccountBalances(); // Wait for the balances
    const rawPrices = await getCryptoPrices();
    const formattedPrices = formatCryptoPrices(rawPrices); // Filter and map the data for easier consumption

    const accountBalance = parseFloat(accountBalances[0].accountBalance);
    let updatedBalance, priceDifference;

    for (const cryptoData of formattedPrices) {
      if (cryptoData.symbol === symbol) {
        priceDifference = cryptoData.price - price; // Calculate the price difference
        updatedBalance = accountBalance - priceDifference * quantity;
        break; // Exit loop early once found
      }
    }

    if (updatedBalance == null) {
      throw new Error("Price for ${symbol} not found.");
    }

    if (updatedBalance < 0) {
      alert(
        "Insufficient Balance: You don't have enough funds to complete this sell."
      );
      return;
    }

    const profitOrLoss = parseFloat((priceDifference * quantity).toFixed(2));
    const sum = profitOrLoss + parseFloat(price) + accountBalance;

    updatedBalance = parseFloat(sum.toFixed(2));
    await updateAccountBalance(accountBalances[0].accountId, updatedBalance);

    const remainingHoldingQuantity = holding.quantity - quantity;

    if (remainingHoldingQuantity != 0) {
      await updateHolding(
        holding.holdingId,
        createHoldingBody(symbol, remainingHoldingQuantity, price)
      );

      await createTransaction(
        buildSellTransactionData(
          symbol,
          quantity,
          price,
          profitOrLoss,
          holding.holdingId
        )
      );
    } else {
      await deleteHolding(holding.holdingId);

      await createTransaction(
        buildSellTransactionWithoutHoldingData(
          symbol,
          quantity,
          price,
          profitOrLoss
        )
      );
    }

    window.location.reload();
  } catch (error) {
    console.error("Error:", error);
  }
};

export default HandleSell;