package com.data.lake.cosmosdb.service;

import com.data.lake.cosmosdb.listeners.OrdersConsumer;
import com.data.lake.cosmosdb.repository.CosmosRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrdersConsumerTest {

    @Mock
    private CosmosRepository zSubOrdersDtRepository;

    @InjectMocks
    private OrdersConsumer ordersConsumer;

    @Test
    public void testConsumeMessages() {
       /* String testMessage = "{\"flag\":\"1234\"}";
        String testKey = "key123";

        for(int i = 0; i<=40;i++){
            ordersConsumer.consumeMessages(testMessage, testKey);
        }

        List<JsonNode> messages = ordersConsumer.getMessageBatch();
        zSubOrdersDtRepository.saveIntoCosmos(messages);

        verify(zSubOrdersDtRepository, times(1)).saveIntoCosmos(messages);*/
    }
}
