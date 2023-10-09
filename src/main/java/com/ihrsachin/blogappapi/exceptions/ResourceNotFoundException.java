package com.ihrsachin.blogappapi.exceptions;

import org.apache.logging.log4j.message.StringFormattedMessage;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, String resourceField, int fieldValue) {
        super(String.format("%s not not found with %s : %d", resourceName, resourceField, fieldValue));
    }
}
