// Create json body for various requests
export const createHoldingBody = (symbol, quantity, price) => ({
  symbol: symbol,
  quantity: quantity,
  price: price,
});

export const buildBuyTransactionBody = (
  symbol,
  quantity,
  price,
  holdingId
) => ({
  assetSymbol: symbol,
  assetQuantity: quantity,
  assetPrice: price,
  transactionType: "BUY",
  holding: {
    holdingId,
  },
});

export const buildSellTransactionData = (
  symbol,
  quantity,
  price,
  profit,
  cryptoPrice,
  holdingId
) => ({
  assetSymbol: symbol,
  assetQuantity: quantity,
  assetPrice: price,
  transactionType: "SELL",
  profit: profit,
  soldAt: cryptoPrice,
  holding: {
    holdingId,
  },
});

export const buildSellTransactionWithoutHoldingData = (
  symbol,
  quantity,
  price,
  profit,
  cryptoPrice
) => ({
  assetSymbol: symbol,
  assetQuantity: quantity,
  assetPrice: price,
  transactionType: "SELL",
  profit: profit,
  soldAt: cryptoPrice,
});