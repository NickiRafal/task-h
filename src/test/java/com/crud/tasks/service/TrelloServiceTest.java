package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TrelloServiceTest {

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @InjectMocks
    private TrelloService trelloService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void fetchTrelloBoards_ReturnsListOfTrelloBoards() {
        // given
        List<TrelloBoardDto> expectedBoards = Collections.singletonList(new TrelloBoardDto());
        when(trelloClient.getTrelloBoards()).thenReturn(expectedBoards);

        // when
        List<TrelloBoardDto> fetchedBoards = trelloService.fetchTrelloBoards();

        // then
        assertEquals(expectedBoards.size(), fetchedBoards.size());
    }

    @Test
    void createTrelloCard_CreatesNewCardAndSendsEmail() {
        // given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test Card", "Test Description", "top", "1");
        CreatedTrelloCardDto createdCard = new CreatedTrelloCardDto("1", "Test Card", "http://test.com");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdCard);
        when(adminConfig.getAdminMail()).thenReturn("admin@example.com");

        // when
        CreatedTrelloCardDto result = trelloService.createTrelloCard(trelloCardDto);

        // then
        assertEquals(createdCard, result);
        verify(emailService, times(1)).send(any(Mail.class));
    }

}
