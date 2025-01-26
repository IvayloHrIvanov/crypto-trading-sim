package com.example.crypto.tradingplatform.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
@ClientEndpoint
public class KrakenWebSocketService {
    @Value("${kraken.websocket.url}")
    private String KRAKEN_WS_URL;

    private Session session = null;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Map<String, JsonNode> cryptoPrices = new HashMap<>();

    public void connect() {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, URI.create(KRAKEN_WS_URL));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("\nConnected to Kraken WebSocket");
        subscribeToTicker();
    }

    @OnMessage
    public void onMessage(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);

            if (jsonNode.has("channel") && "ticker".equals(jsonNode.get("channel").asText())) {
                cryptoPrices.put(jsonNode.get("data").get(0).get("symbol").asText(), jsonNode);
            }

            System.out.println(jsonNode.toPrettyString() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose() {
        this.session = null;
        System.out.println("Disconnected from Kraken WebSocket");
    }

    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        reconnect();
    }

    private void reconnect() {
        System.out.println("Reconnecting to Kraken WebSocket...");
        connect();
    }

    private void subscribeToTicker() {
        try {
            String payload = createSubscribePayload();
            session.getAsyncRemote().sendText(payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String createSubscribePayload() {
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


    public Map<String, JsonNode> getCryptoPrices() {
        return cryptoPrices;
    }
}