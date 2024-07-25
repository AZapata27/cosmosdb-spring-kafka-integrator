package com.data.lake.cosmosdb.service;

import com.data.lake.cosmosdb.listeners.TitulateConsumer;
import com.data.lake.cosmosdb.repository.CosmosRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TitulateConsumerTest {
    @Mock
    private CosmosRepository zSubTitulateDtRepository;

    @InjectMocks
    private TitulateConsumer titulateConsumer;

    /*@Test
    public void testConsumeMessages() {
        String testMessage = "{\"flag\":\"1234\"}";
        String testKey = "key123";

        for(int i = 0; i<=40;i++){
            titulateConsumer.consumeMessages(testMessage, testKey);
        }

        List<JsonNode> messages = titulateConsumer.getMessageBatch();
        zSubTitulateDtRepository.saveIntoCosmos(messages);

        verify(zSubTitulateDtRepository, times(1)).saveIntoCosmos(messages);
    }*/
}
