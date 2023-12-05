package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DbServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private DbService dbService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTasks_ReturnsListOfTasks() {
        // given
        List<Task> tasks = Arrays.asList(new Task(), new Task());
        when(taskRepository.findAll()).thenReturn(tasks);

        // when
        List<Task> retrievedTasks = dbService.getAllTasks();

        // then
        assertEquals(tasks.size(), retrievedTasks.size());
    }

    @Test
    void getTask_ExistingTaskId_ReturnsTask() throws TaskNotFoundException {
        // given
        Long taskId = 1L;
        Task task = new Task();
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        // when
        Task retrievedTask = dbService.getTask(taskId);

        // then
        assertEquals(task, retrievedTask);
    }

    @Test
    void getTask_NonExistingTaskId_ThrowsTaskNotFoundException() {
        // given
        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // then
        assertThrows(TaskNotFoundException.class, () -> dbService.getTask(taskId));
    }
    @Test
    void deleteTask_ExistingTaskId_DeletesTask() {
        // given
        Long taskId = 1L;

        // when
        dbService.deleteTask(taskId);

        // then
        verify(taskRepository, times(1)).deleteById(taskId);
    }
    @Test
    void saveTask_NewTask_SavesTask() {
        // given
        Task task = new Task();

        // when
        dbService.saveTask(task);

        // then
        verify(taskRepository, times(1)).save(task);
    }



}
