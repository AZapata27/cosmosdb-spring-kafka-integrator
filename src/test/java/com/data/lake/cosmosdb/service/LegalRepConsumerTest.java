package com.data.lake.cosmosdb.service;

import com.data.lake.cosmosdb.listeners.LegalRepConsumer;
import com.data.lake.cosmosdb.repository.CosmosRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LegalRepConsumerTest {
    @Mock
    private CosmosRepository zsubLegalRepDtRepository;

    @InjectMocks
    private LegalRepConsumer legalRepConsumer;

    /*@Test
    public void testConsumeMessages() {
        String testMessage = "{\"flag\":\"1234\"}";
        String testKey = "key123";

        for(int i = 0; i<=40;i++){
            legalRepConsumer.consumeMessages(testMessage, testKey);
        }

    }*/
}
