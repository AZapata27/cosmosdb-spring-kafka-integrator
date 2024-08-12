package com.data.lake.cosmosdb.repository;

import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.CosmosException;
import com.azure.cosmos.models.CosmosItemRequestOptions;
import com.azure.cosmos.models.CosmosItemResponse;
import com.azure.cosmos.models.PartitionKey;
import com.data.lake.cosmosdb.config.TopicsConfig;
import com.data.lake.cosmosdb.exception.CosmosBussinesException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class CosmosRepository {

    private static final String ID_FIELD = "id";
    private static final String FLAG_FIELD = "flag";
    private static final String RETRY_AFTER_HEADER = "x-ms-retry-after-ms";

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public CosmosRepository(ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Retry(name = "cosmosRetry")
    public void saveIntoCosmos(String message, String key, CosmosContainer cosmosContainer, String topicFrom) {

        try {

            JsonNode jsonToSave = prepareJsonForSave(message, key);

            CosmosItemResponse<JsonNode> response = cosmosContainer.upsertItem(jsonToSave, new PartitionKey(key), new CosmosItemRequestOptions());

            if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
                // Registro exitoso
            } else {
                // Manejo de otros códigos de estado
                handleProcessingException(message,key,response,topicFrom);
            }

        } catch (CosmosException e) {
            handleCosmosException(e);
        } catch (JsonProcessingException e) {
            handleJsonProcessingException(message,key,e,topicFrom);
        }
    }

    private JsonNode prepareJsonForSave(String message, String key) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(message);
        return addFields(jsonNode, key, key);
    }

    private JsonNode addFields(JsonNode message, String idValue, String flagValue) {
        ObjectNode objectNode = (ObjectNode) message;
        objectNode.put(ID_FIELD, idValue);
        objectNode.put(FLAG_FIELD, flagValue);
        return objectNode;
    }

    private void handleCosmosException(CosmosException e) {
        if (e.getStatusCode() == 429) {
            handleRateLimitExceeded(e);
        }
        throw new CosmosBussinesException("Error saving into cosmos:", e);
    }

    private void handleRateLimitExceeded(CosmosException e) {

        String retryAfterHeader = e.getResponseHeaders().get(RETRY_AFTER_HEADER);

        if(retryAfterHeader != null) {
            long retryAfterMs = Long.parseLong(retryAfterHeader);
            try {
                Thread.sleep(retryAfterMs);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                throw new CosmosBussinesException("Thread Sleep Exception", ie);
            }
            throw new CosmosBussinesException("Rate limit exceeded", e);
        }

    }

    private void handleJsonProcessingException(String message, String key, JsonProcessingException e, String topicFrom) {
        log.error("Error processing json message: {}", message, e);
        kafkaTemplate.send(TopicsConfig.getDLTName(topicFrom) , key,message);
    }

    private void handleProcessingException(String message, String key, CosmosItemResponse<JsonNode> e, String topicFrom) {
        log.error("Error processing json message: {} con response {}", message, e);
        kafkaTemplate.send(TopicsConfig.getDLTName(topicFrom), key,message);
    }
}



































