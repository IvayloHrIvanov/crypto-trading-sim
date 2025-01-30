import { getAccountBalances, updateAccountBalance } from "@/api/AccountBalanceRequests";
import { createHolding, getHoldings, updateHolding } from "@/api/HoldingRequests";
import { createTransaction } from "@/api/TransactionRequests";
import { buildBuyTransactionBody, createHoldingBody } from "@/util/RequestUtils";

// Function to handle the process of buying cryptocurrency
const HandleBuy = async (symbol, quantity, price) => {
  try {
    if (quantity < 1 || quantity == null) {
      alert(
        "Cannot buy 0 or negative cryptocurrency."
      );
      return;
    }

    const accountBalances = await getAccountBalances();
    // Calculate the available balance after the purchase
    const availableBalance = accountBalances[0].accountBalance - price * quantity;

    if (availableBalance < 0) {
      alert(
        "Insufficient Balance: You don't have enough funds to complete this purchase."
      );
      return;
    }

    await updateAccountBalance(accountBalances[0].accountId, availableBalance);

    // Fetch existing holdings to check if the user already owns the cryptocurrency at the same price
    const existingHoldings = await getHoldings();
    let checkExistingHolding = false;
    let existingHoldingId, newHoldingQuantity;

    for(let i = 0; i < existingHoldings.length; i++){
      if(existingHoldings[i].symbol == symbol && existingHoldings[i].price == price){ // Check if there is an existing Holding
        existingHoldingId = existingHoldings[i].holdingId;
        newHoldingQuantity = parseInt(existingHoldings[i].quantity, 10) + parseInt(quantity, 10); // Calculate the new total quantity
        checkExistingHolding = true;
        break;
      }
    }

    let updatedOrNewHolding
    
    if(checkExistingHolding){
      // If there is an existing Holding - update it
      updatedOrNewHolding = await updateHolding(existingHoldingId, createHoldingBody(symbol, newHoldingQuantity, price));
    } else {
      // If there isn't an existing Holding - create new one
      updatedOrNewHolding = await createHolding(createHoldingBody(symbol, quantity, price));
    }

    // Create a transaction record for the buy operation
    await createTransaction(buildBuyTransactionBody(symbol, quantity, price, updatedOrNewHolding.holdingId));

    window.location.reload();
  } catch (error) {
    console.error("Error:", error);
  }
};

export default HandleBuy;