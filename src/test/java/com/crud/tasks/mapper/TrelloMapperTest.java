package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrelloMapperTest {

    @Test
    public void shouldMapToBoards() {
        // given
        TrelloMapper trelloMapper = new TrelloMapper();
        List<TrelloBoardDto> trelloBoardDtos = Collections.singletonList(new TrelloBoardDto("1", "Test Board", Collections.emptyList()));

        // when
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        // then
        assertEquals(1, trelloBoards.size());
        assertEquals(trelloBoardDtos.get(0).getId(), trelloBoards.get(0).getId());
        assertEquals(trelloBoardDtos.get(0).getName(), trelloBoards.get(0).getName());
        assertTrue(trelloBoards.get(0).getLists().isEmpty());
    }

    @Test
    public void shouldMapToBoardsDto() {
        // given
        TrelloMapper trelloMapper = new TrelloMapper();
        List<TrelloBoard> trelloBoards = Collections.singletonList(new TrelloBoard("1", "Test Board", Collections.emptyList()));

        // when
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);

        // then
        assertEquals(1, trelloBoardDtos.size());
        assertEquals(trelloBoards.get(0).getId(), trelloBoardDtos.get(0).getId());
        assertEquals(trelloBoards.get(0).getName(), trelloBoardDtos.get(0).getName());
        assertTrue(trelloBoardDtos.get(0).getLists().isEmpty());
    }
    @Test
    public void shouldMapToBoardsWithMultipleLists() {
        // given
        TrelloMapper trelloMapper = new TrelloMapper();
        List<TrelloListDto> trelloListDtos = List.of(
                new TrelloListDto("1", "List 1", false),
                new TrelloListDto("2", "List 2", true)
        );
        List<TrelloBoardDto> trelloBoardDtos = Collections.singletonList(new TrelloBoardDto("1", "Test Board", trelloListDtos));

        // when
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        // then
        assertEquals(1, trelloBoards.size());
        assertEquals(trelloBoardDtos.get(0).getId(), trelloBoards.get(0).getId());
        assertEquals(trelloBoardDtos.get(0).getName(), trelloBoards.get(0).getName());
        assertFalse(trelloBoards.get(0).getLists().isEmpty());
        assertEquals(2, trelloBoards.get(0).getLists().size());
    }

    @Test
    public void shouldMapToBoardsDtoWithMultipleLists() {
        // given
        TrelloMapper trelloMapper = new TrelloMapper();
        List<TrelloList> trelloLists = List.of(
                new TrelloList("1", "List 1", false),
                new TrelloList("2", "List 2", true)
        );
        List<TrelloBoard> trelloBoards = Collections.singletonList(new TrelloBoard("1", "Test Board", trelloLists));

        // when
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);

        // then
        assertEquals(1, trelloBoardDtos.size());
        assertEquals(trelloBoards.get(0).getId(), trelloBoardDtos.get(0).getId());
        assertEquals(trelloBoards.get(0).getName(), trelloBoardDtos.get(0).getName());
        assertFalse(trelloBoardDtos.get(0).getLists().isEmpty());
        assertEquals(2, trelloBoardDtos.get(0).getLists().size());
    }

}
