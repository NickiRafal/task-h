package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@SuppressWarnings("ALL")
@Component
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class TrelloCardDto {
    private String name;
    private String description;
    private String pos;
    private String listId;

}
