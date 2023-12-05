package com.crud.tasks.trello.validator;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.slf4j.LoggerFactory;

public class TrelloValidatorTest {

    private TrelloValidator trelloValidator;
    private ListAppender<ILoggingEvent> listAppender;

    @BeforeEach
    public void setUp() {
        trelloValidator = new TrelloValidator();
        Logger logger = LoggerFactory.getLogger(TrelloValidator.class);
        listAppender = new ListAppender<>();
        listAppender.start();
        ch.qos.logback.classic.Logger logbackLogger = (ch.qos.logback.classic.Logger) logger;
        logbackLogger.addAppender(listAppender);
    }

    @Test
    public void testValidateCard() {
        // Given
        TrelloCard trelloCard = new TrelloCard("test Card");

        // When
        trelloValidator.validateCard(trelloCard);

        // Then
        assertNotNull(listAppender);
        assertEquals(1, listAppender.list.size());
        assertEquals("Someone is testing my application!", listAppender.list.get(0).getMessage());
    }
    @Test
    public void testValidateTrelloBoards() {
        // Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("test"));
        trelloBoards.add(new TrelloBoard("Valid Board"));

        // When
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);

        // Then
        assertEquals(1, filteredBoards.size());
        assertEquals("Valid Board", filteredBoards.get(0).getName());
    }

}