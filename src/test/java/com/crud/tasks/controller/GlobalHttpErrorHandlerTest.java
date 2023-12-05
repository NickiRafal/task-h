package com.crud.tasks.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalHttpErrorHandlerTest {

    @Test
    void handleTaskNotFoundException_ReturnsBadRequest() {
        // Given
        GlobalHttpErrorHandler errorHandler = new GlobalHttpErrorHandler();

        // When
        ResponseEntity<Object> response = errorHandler.handleTaskNotFoundException(new TaskNotFoundException());

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Task with given id doesn't exist", response.getBody());
    }
}
