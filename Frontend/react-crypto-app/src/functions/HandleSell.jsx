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

// Function to handle the process of selling cryptocurrency
const HandleSell = async (holding, symbol, quantity, price) => {
  try {
    if (quantity < 1 || quantity == null) {
      alert(
        "Cannot sell 0 or negative cryptocurrency."
      );
      return;
    }

    // Fetch the current account balances and crypto prices
    const accountBalances = await getAccountBalances();
    const rawPrices = await getCryptoPrices();
    const formattedPrices = formatCryptoPrices(rawPrices); // Filter and map the data for easier consumption

    const accountBalance = parseFloat(accountBalances[0].accountBalance);
    let updatedBalance, priceDifference, cryptoPrice;

    // Find the price of the symbol being sold
    for (const cryptoData of formattedPrices) {
      if (cryptoData.symbol === symbol) {
        cryptoPrice = parseFloat(cryptoData.price).toFixed(2);
        priceDifference = cryptoData.price - price; // Calculate the price difference
        updatedBalance = accountBalance - priceDifference * quantity;
        break;
      }
    }

    if (updatedBalance == null) {
      throw new Error(`Price for ${symbol} not found.`);
    }

    if (updatedBalance < 0) {
      alert(
        "Insufficient Balance: You don't have enough funds to complete this sell."
      );
      return;
    }

    const profitOrLoss = parseFloat((priceDifference * quantity).toFixed(2)); // Calculate the profit or loss
    const sum = profitOrLoss + parseFloat(price) + accountBalance; // Calculate updated Balance

    updatedBalance = parseFloat(sum.toFixed(2));
    await updateAccountBalance(accountBalances[0].accountId, updatedBalance); // Update the account balance with the new balance

    const remainingHoldingQuantity = holding.quantity - quantity;

    // If there is any remaining quantity (didn't sell all), update the holding and record the transaction
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
          cryptoPrice,
          holding.holdingId
        )
      );
    } else { // If there is any remaining quantity, delete the holding and record the transaction
      await deleteHolding(holding.holdingId);

      await createTransaction(
        buildSellTransactionWithoutHoldingData(
          symbol,
          quantity,
          price,
          cryptoPrice,
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