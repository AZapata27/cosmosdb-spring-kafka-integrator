package com.data.lake.cosmosdb.config;

import com.azure.cosmos.*;
import com.azure.cosmos.models.CosmosContainerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@Slf4j
public class CosmosDatasourceConfiguration {

    @Value("${azure.cosmosdb.uri}")
    String cosmosEndpointDB;

    @Value("${azure.cosmosdb.key}")
    String cosmosKeyDB;

    @Value("${azure.cosmosdb.database}")
    String databaseName;


    @Bean
    public CosmosClient cosmosClient() {

        return new CosmosClientBuilder()
                .endpoint(cosmosEndpointDB)
                .key(cosmosKeyDB)
                .directMode()
                .consistencyLevel(ConsistencyLevel.EVENTUAL)
                .contentResponseOnWriteEnabled(false)
                .buildClient();
    }


    @Bean("finances")
    public CosmosContainer financesContainer(CosmosClient cosmosClient,
                                             @Value("${azure.cosmosdb.collection.zsub.finances}") String containerName) {
        return createCosmosContainer(cosmosClient, databaseName, containerName);
    }

    @Bean("category")
    public CosmosContainer categoryContainer(CosmosClient cosmosClient,
                                             @Value("${azure.cosmosdb.collection.zsub.cat}") String containerName) {
        return createCosmosContainer(cosmosClient, databaseName, containerName);
    }

    @Bean("company")
    public CosmosContainer companyContainer(CosmosClient cosmosClient,
                                             @Value("${azure.cosmosdb.collection.zsub.company}") String containerName) {
        return createCosmosContainer(cosmosClient, databaseName, containerName);
    }

    @Bean("legalRep")
    public CosmosContainer legalRepContainer(CosmosClient cosmosClient,
                                             @Value("${azure.cosmosdb.collection.zsub.legal.rep}") String containerName) {
        return createCosmosContainer(cosmosClient, databaseName, containerName);
    }

    @Bean("orders")
    public CosmosContainer ordersContainer(CosmosClient cosmosClient,
                                           @Value("${azure.cosmosdb.collection.zsub.orders}") String containerName) {
        return createCosmosContainer(cosmosClient, databaseName, containerName);
    }

    @Bean("pac")
    public CosmosContainer pacContainer(CosmosClient cosmosClient,
                                             @Value("${azure.cosmosdb.collection.zsub.pac}") String containerName) {
        return createCosmosContainer(cosmosClient, databaseName, containerName);
    }

    @Bean("titulate")
    public CosmosContainer titulateContainer(CosmosClient cosmosClient,
                                             @Value("${azure.cosmosdb.collection.zsub.titulate}") String containerName) {
        return createCosmosContainer(cosmosClient, databaseName, containerName);
    }



    private CosmosContainer createCosmosContainer(CosmosClient cosmosClient, String databaseName, String containerName) {
        CosmosContainer container;
        try {
            CosmosDatabase database = cosmosClient.getDatabase(databaseName);
            container = database.getContainer(containerName);

            CosmosContainerResponse response = container.read();
            log.info("Container initialization for '{}' read successfully, status code: {}", containerName, response.getStatusCode());

        } catch (CosmosException e) {
            log.error("Error en la interacción con Cosmos DB para el contenedor'{}': {}", containerName, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("Error general presentado para el contenedor '{}': {}", containerName, e.getMessage(), e);
            throw e;
        }
        return container;
    }
}
