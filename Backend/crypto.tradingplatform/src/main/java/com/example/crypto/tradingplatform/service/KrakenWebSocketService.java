package com.example.crypto.tradingplatform.service;

import com.example.crypto.tradingplatform.util.WebSocketJsonHelperUtils;
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

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private Session session = null;
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
            JsonNode jsonNode = OBJECT_MAPPER.readTree(message);

            if (jsonNode.has("channel") && "ticker".equals(jsonNode.path("channel").asText())) {
                JsonNode dataNode = jsonNode.path("data");
                if (dataNode.isArray() && !dataNode.isEmpty()) {
                    JsonNode firstData = dataNode.path(0);
                    if (firstData.has("symbol") && !firstData.isEmpty()) {
                        cryptoPrices.put(firstData.get("symbol").asText(), jsonNode);
                    }
                }
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
            String body = WebSocketJsonHelperUtils.createSubscribeMessage();
            session.getAsyncRemote().sendText(body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, JsonNode> getCryptoPrices() {
        return cryptoPrices;
    }
}