export const handleApiError = (error, customMessage = "") => {
    // Log the error for debugging
    console.error(customMessage, error);
  
    // Handle different error scenarios
    if (error.response) {
      // Server responded with a status other than 2xx
      console.error("Response error:", error.response.data);
    } else if (error.request) {
      // No response received from the server
      console.error("Request error:", error.request);
    } else {
      // Other errors
      console.error("Error setting up request:", error.message);
    }
  };
  