import axios from "axios";

import { handleApiError } from "@/util/ErrorUtils";

export const getCryptoPrices = async () => {
  try {
    const response = await axios.get("/crypto-prices");
    return response.data;
  } catch (error) {
    handleApiError(error, "Error fetching crypto prices: ");
  }
};