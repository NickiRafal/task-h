package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    @Test
    public void shouldMapToTaskDtoList() {
        // given
        TaskMapper taskMapper = new TaskMapper();
        List<Task> tasks = Arrays.asList(
                new Task(1L, "Task 1", "Content 1"),
                new Task(2L, "Task 2", "Content 2")
        );

        // when
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);

        // then
        assertEquals(tasks.size(), taskDtos.size());
        assertEquals(tasks.get(0).getId(), taskDtos.get(0).getId());
        assertEquals(tasks.get(1).getTitle(), taskDtos.get(1).getTitle());
        // Test other mappings...
    }
    @Test
    public void shouldMapEmptyTaskListToDtoList() {
        // given
        TaskMapper taskMapper = new TaskMapper();
        List<Task> tasks = Collections.emptyList();

        // when
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);

        // then
        assertTrue(taskDtos.isEmpty());
    }
}
