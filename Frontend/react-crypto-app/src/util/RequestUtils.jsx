export const createHoldingBody = (symbol, quantity, price) => ({
  symbol: symbol,
  quantity: quantity,
  price: price,
});

export const buildBuyTransactionBody = (symbol, quantity, price, holdingId) => ({
  assetSymbol: symbol,
  assetQuantity: quantity,
  assetPrice: price,
  transactionType: "BUY",
  holding: {
    holdingId,
  },
});

export const buildSellTransactionData = (symbol, quantity, price, profit, holdingId) => ({
  assetSymbol: symbol,
  assetQuantity: quantity,
  assetPrice: price,
  transactionType: "SELL",
  profit: profit,
  holding: {
    holdingId,
  },
});

export const buildSellTransactionWithoutHoldingData = (symbol, quantity, price, profit) => ({
  assetSymbol: symbol,
  assetQuantity: quantity,
  assetPrice: price,
  transactionType: "SELL",
  profit: profit,
});