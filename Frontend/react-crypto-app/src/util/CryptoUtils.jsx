// Formats crypto prices from the API response
export const formatCryptoPrices = (rawPrices) => {
  return Object.keys(rawPrices).map((key) => {
    const data = rawPrices[key].data[0];
    return {
      symbol: data.symbol, // Example: "BTC/USD"
      price: data.last, // Cryptocurrency price
    };
  });
};

export const updateCryptoPrices = (prevPrices, formattedPrices) => {
  const updatedPrices = prevPrices.slice();
  formattedPrices.forEach((newPrice) => {
    const index = updatedPrices.findIndex((p) => p.symbol === newPrice.symbol);
    if (index !== -1) {
      updatedPrices[index] = newPrice; // Update existing entry
    } else {
      updatedPrices.push(newPrice); // Add new entry
    }
  });
  return updatedPrices;
};