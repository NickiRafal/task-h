package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TrelloControllerTest {

    @Mock
    private TrelloFacade trelloFacade;

    @InjectMocks
    private TrelloController trelloController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTrelloBoards_ReturnsListOfTrelloBoards() {
        // given
        List<TrelloBoardDto> expectedBoards = Collections.singletonList(new TrelloBoardDto());
        when(trelloFacade.fetchTrelloBoards()).thenReturn(expectedBoards);

        // when
        ResponseEntity<List<TrelloBoardDto>> response = trelloController.getTrelloBoards();

        // then
        assertEquals(expectedBoards, response.getBody());
    }

    @Test
    void createTrelloCard_CreatesNewTrelloCard() {
        // given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test Card", "Test Description", "top", "1");
        CreatedTrelloCardDto createdCard = new CreatedTrelloCardDto("1", "Test Card", "http://test.com");
        when(trelloFacade.createCard(trelloCardDto)).thenReturn(createdCard);

        // when
        ResponseEntity<CreatedTrelloCardDto> response = trelloController.createTrelloCard(trelloCardDto);

        // then
        assertEquals(createdCard, response.getBody());
    }

}
