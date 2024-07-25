package com.data.lake.cosmosdb.service;

import com.data.lake.cosmosdb.listeners.CompanyConsumer;
import com.data.lake.cosmosdb.repository.CosmosRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CompanyConsumerTest {
    @Mock
    private CosmosRepository zSubCompanyDtRespository;

    @InjectMocks
    private CompanyConsumer companyConsumer;

    /*@Test
    public void testConsumeMessages() {
        String testMessage = "{\"flag\":\"1234\"}";
        String testKey = "key123";

        for(int i = 0; i<=40;i++){
            companyConsumer.consumeMessages(testMessage, testKey);
        }
    }*/
}