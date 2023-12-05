package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaskControllerTest {

    @Mock
    private DbService dbService;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskController taskController;

    private Task task;
    private TaskDto taskDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTasks_ReturnsListOfTasks() {
        // Given
        List<Task> tasks = Collections.singletonList(task);
        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(Collections.singletonList(taskDto));

        // When
        ResponseEntity<List<TaskDto>> response = taskController.getTasks();

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getTask_WithValidId_ReturnsTask() throws TaskNotFoundException {
        // Given
        when(dbService.getTask(1L)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        // When
        ResponseEntity<TaskDto> response = taskController.getTask(1L);

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(taskDto, response.getBody());
    }

    @Test
    void deleteTask_WithValidId_DeletesTask() {
        // When
        ResponseEntity<Void> response = taskController.deleteTask(1L);

        // Then
        assertEquals(200, response.getStatusCodeValue());
        verify(dbService, times(1)).deleteTask(1L);
    }

    @Test
    void updateTask_WithValidTaskDto_UpdatesTask() {
        // Given
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);

        // When
        ResponseEntity<TaskDto> response = taskController.updateTask(taskDto);

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(taskDto, response.getBody());
    }

    @Test
    void createTask_WithValidTaskDto_CreatesTask() {
        // Given
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);

        // When
        ResponseEntity<Void> response = taskController.createTask(taskDto);

        // Then
        assertEquals(200, response.getStatusCodeValue());
    }
}

