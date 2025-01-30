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

    let updatedBalance, priceDifference;

    for (const priceData of formattedPrices) {
      if (priceData.symbol === symbol) {
        priceDifference = priceData.price - price;
        updatedBalance = accountBalances[0].accountBalance - priceDifference;
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

    await updateAccountBalance(accountBalances[0].accountId, updatedBalance);

    const remainingHoldingQuantity = holding.quantity - quantity;
    const profit = priceDifference * quantity;

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
          profit,
          holding.holdingId
        )
      );
    } else {
      await deleteHolding(holding.holdingId);

      await createTransaction(
        buildSellTransactionWithoutHoldingData(symbol, quantity, price, profit)
      );
    }

    window.location.reload();
  } catch (error) {
    console.error("Error:", error);
  }
};

export default HandleSell;