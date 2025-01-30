import axios from "@/config/axiosConfig";

import { handleApiError } from "@/util/ErrorUtils";

export const getTransactions = async () => {
  try {
    const response = await axios.get("/transaction");
    console.log("Transactions Get:", response.data);

    const transactions = response.data.map((item) => {
      return {
        transactionId: item.transactionId,
        assetSymbol: item.assetSymbol,
        assetQuantity: item.assetQuantity,
        assetPrice: item.assetPrice,
        transactionType: item.transactionType,
        profit: item.profit,
        holding: item.holding
      };
    });

    return transactions;
  } catch (error) {
    handleApiError(error, "Error getting transaction: ");
  }
};

export const createTransaction = async (transaction) => {
  try {
    const response = await axios.post("/transaction", transaction);
    console.log("Transaction Created:", response.data);
    return response.data;
  } catch (error) {
    handleApiError(error, "Error creating transaction: ");
  }
};

export const updateTransaction = async (transactionId, transaction) => {
  try {
    const response = await axios.put(
      `/transaction/${transactionId}`,
      transaction
    );
    console.log("Transaction Updated:", response.data);
  } catch (error) {
    handleApiError(error, "Error updating transaction: ");
  }
};

export const deleteTransaction = async (transactionId) => {
  try {
    const response = await axios.delete(
      `/transaction/${transactionId}`
    );
    console.log("Transaction Deleted:", response.data);
  } catch (error) {
    handleApiError(error, "Error deleting transaction: ");
  }
};

export const deleteAllTransactions = async () => {
  try {
    const response = await axios.delete("/transaction/deleteAll");
    console.log("Transactions Deleted:", response.data);
  } catch (error) {
    handleApiError(error, "Error deleting all transaction: ");
  }
};