import axios from "axios";

const API_URL = "http://localhost:8080/api/crypto-prices";

export const getCryptoPrices = async () => {
  try {
    const response = await axios.get(API_URL);
    return response.data;
  } catch (error) {
    // Log the error for debugging
    console.error("Error fetching crypto prices:", error);

    // You can handle different error scenarios here:
    if (error.response) {
      // Server responded with a status other than 2xx
      console.error("Response error:", error.response.data);
    } else if (error.request) {
      // No response received from the server
      console.error("Request error:", error.request);
    } else {
      // Other errors (e.g., setting up the request)
      console.error("Error setting up request:", error.message);
    }
  }
};