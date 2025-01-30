import axios from "@/config/axiosConfig";

import { handleApiError } from "@/util/ErrorUtils";

export const getAccountBalances = async () => {
  try {
    const response = await axios.get("/account-balance");
    console.log("Accounts balance Get:", response.data);

    const accounts = response.data.map((item) => {
      return {
        accountId: item.accountId,
        accountBalance: item.accountBalance
      };
    });

    return accounts;
  } catch (error) {
    handleApiError(error, "Error getting account balance: ");
  }
};

export const createAccountBalance = async (accountBalance) => {
  try {
    const response = await axios.post("/account-balance", accountBalance);
    console.log("Account balance Created:", response.data);
    return response.data;
  } catch (error) {
    handleApiError(error, "Error creating account balance: ");
  }
};

export const createAccountWithDefaultBalance = async () => {
  try {
    const response = await axios.post("/account-balance/default");
    console.log("Default Account balance Created:", response.data);
    return response.data;
  } catch (error) {
    handleApiError(error, "Error creating default account balance: ");
  }
};

export const updateAccountBalance = async (accountId, accountBalance) => {
  try {
    const response = await axios.put(`/account-balance/${accountId}`, {
      accountBalance: accountBalance,
    });
    console.log("Account balance Updated:", response.data);
  } catch (error) {
    handleApiError(error, "Error updating account balance: ");
  }
};

export const deleteAccountBalance = async (accountId) => {
  try {
    const response = await axios.delete(`/account-balance/${accountId}`);
    console.log("Account balance Deleted:", response.data);
  } catch (error) {
    handleApiError(error, "Error deleting account balance: ");
  }
};

export const deleteAllAccountBalances = async () => {
  try {
    const response = await axios.delete("/account-balance/deleteAll");
    console.log("Account balances Deleted:", response.data);
  } catch (error) {
    handleApiError(error, "Error deleting all account balances: ");
  }
};