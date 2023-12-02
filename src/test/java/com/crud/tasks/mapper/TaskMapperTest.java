package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskMapperTest {

    @Test
    public void shouldMapToTask() {
        // given
        TaskMapper taskMapper = new TaskMapper();
        TaskDto taskDto = new TaskDto(1L, "Test Task", "Test Content");

        // when
        Task task = taskMapper.mapToTask(taskDto);

        // then
        assertEquals(taskDto.getId(), task.getId());
        assertEquals(taskDto.getTitle(), task.getTitle());
        assertEquals(taskDto.getContent(), task.getContent());
    }

    @Test
    public void shouldMapToTaskDto() {
        // given
        TaskMapper taskMapper = new TaskMapper();
        Task task = new Task(1L, "Test Task", "Test Content");

        // when
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        // then
        assertEquals(task.getId(), taskDto.getId());
        assertEquals(task.getTitle(), taskDto.getTitle());
        assertEquals(task.getContent(), taskDto.getContent());
    }
}
