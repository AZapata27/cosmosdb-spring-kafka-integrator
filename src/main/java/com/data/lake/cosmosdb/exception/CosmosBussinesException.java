package com.data.lake.cosmosdb.exception;

public class CosmosBussinesException extends RuntimeException {

    public CosmosBussinesException(String message){
        super(message);
    }

    public CosmosBussinesException(String message, Throwable cause) {
        super(message,cause);
    }
}
