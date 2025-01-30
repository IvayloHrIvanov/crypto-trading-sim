import axios from "@/config/axiosConfig";

import { handleApiError } from "@/util/ErrorUtils";

export const getHoldings = async () => {
  try {
    const response = await axios.get("/holding");
    console.log("Holdings Get:", response.data);

    const holdings = response.data.map((item) => {
      return {
        holdingId: item.holdingId,
        symbol: item.symbol,
        quantity: item.quantity,
        price: item.price,
        transactions: item.transactions
      };
    });

    return holdings;
  } catch (error) {
    handleApiError(error, "Error getting transaction: ");
  }
};

export const createHolding = async (holding) => {
  try {
    const response = await axios.post("/holding", holding);
    console.log("Holding Created:", response.data);
    return response.data;
  } catch (error) {
    handleApiError(error, "Error creating holding: ");
  }
};

export const updateHolding = async (holdingId, holding) => {
  try {
    const response = await axios.put(
      `/holding/${holdingId}`,
      holding
    );
    console.log("Holding Updated:", response.data);
    return response.data;
  } catch (error) {
    handleApiError(error, "Error updating holding: ");
  }
};

export const deleteHolding = async (holdingId) => {
  try {
    const response = await axios.delete(`/holding/${holdingId}`);
    console.log("Holding Deleted:", response.data);
  } catch (error) {
    handleApiError(error, "Error deleting holding: ");
  }
};

export const deleteAllHoldings = async () => {
  try {
    const response = await axios.delete("/holding/deleteAll");
    console.log("Holdings Deleted:", response.data);
  } catch (error) {
    handleApiError(error, "Error deleting all holdings: ");
  }
};