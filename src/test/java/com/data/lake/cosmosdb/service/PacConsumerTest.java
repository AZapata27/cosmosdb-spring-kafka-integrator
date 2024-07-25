package com.data.lake.cosmosdb.service;

import com.data.lake.cosmosdb.listeners.PacConsumer;
import com.data.lake.cosmosdb.repository.CosmosRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PacConsumerTest {

    @Mock
    private CosmosRepository zSubPacDtRepository;

    @InjectMocks
    private PacConsumer pacConsumer;

    /*@Test
    public void testConsumeMessages() {
        String testMessage = "{\"flag\":\"1234\"}";
        String testKey = "key123";

        for(int i = 0; i<=40;i++){
            pacConsumer.consumeMessages(testMessage, testKey);
        }

        List<JsonNode> messages = pacConsumer.getMessageBatch();
        zSubPacDtRepository.saveIntoCosmos(messages);

        verify(zSubPacDtRepository, times(1)).saveIntoCosmos(messages);
    }*/
}
