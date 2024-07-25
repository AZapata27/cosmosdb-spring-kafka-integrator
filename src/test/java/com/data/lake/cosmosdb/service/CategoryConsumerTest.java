package com.data.lake.cosmosdb.service;

import com.data.lake.cosmosdb.listeners.CategoryConsumer;
import com.data.lake.cosmosdb.repository.CosmosRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CategoryConsumerTest {
    @Mock
    private CosmosRepository zSubCatTdocbpRepository;

    @InjectMocks
    private CategoryConsumer categoryConsumer;

    /*@Test
    public void testConsumeMessages() {
        String testMessage = "{\"flag\":\"1234\"}";
        String testKey = "key123";

        for(int i = 0; i<=40;i++){
            categoryConsumer.consumeMessages(testMessage, testKey);
        }

        List<JsonNode> messages = categoryConsumer.getMessageBatch();
        zSubCatTdocbpRepository.saveIntoCosmos(messages);

        verify(zSubCatTdocbpRepository, times(1)).saveIntoCosmos(messages);
    }*/
}
