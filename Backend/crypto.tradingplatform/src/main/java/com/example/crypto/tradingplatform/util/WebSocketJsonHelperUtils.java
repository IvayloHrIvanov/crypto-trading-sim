package com.example.crypto.tradingplatform.util;

public class WebSocketJsonHelperUtils {

    /**
     * Creates a Kraken WebSocket ticker subscribe request/message in JSON format
     *
     * @return JSON string representing the Kraken WebSocket subscribe schema
     */
    public static String createSubscribeMessage() {
        return "{" +
                "\"method\": \"subscribe\"," +
                "\"params\": {" +
                    "\"channel\": \"ticker\"," +
                    "\"symbol\": [" +
                        "\"BTC/USD\", \"ETH/USD\", \"XRP/USD\"," +
                        "\"ADA/USD\", \"DOGE/USD\", \"USDC/USD\"," +
                        "\"SOL/USD\", \"LTC/USD\", \"MANA/USD\"," +
                        "\"MATIC/USD\", \"AVAX/USD\", \"GST/USD\"," +
                        "\"TRX/USD\", \"BAT/USD\", \"DAI/USD\"," +
                        "\"SUI/USD\", \"GALA/USD\", \"DOT/USD\"," +
                        "\"XLM/USD\", \"ETC/USD\"" +
                    "]" +
                "}" +
                "}";
    }
}