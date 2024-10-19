package com.skb.TaskManagementService.service;

import com.skb.TaskManagementService.model.Task;
import com.skb.TaskManagementService.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        task = new Task();
        task.setTaskId(1L);
        task.setStatus("Pending");
        task.setAssignedTo("user123");
    }

    @Test
    void testGetAllTasks() {
        // Arrange
        when(taskRepository.findAll()).thenReturn(List.of(task));

        // Act
        List<Task> tasks = taskService.getAllTasks();

        // Assert
        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(0));
    }

    @Test
    void testCreateTask() {
        // Arrange
        when(taskRepository.save(task)).thenReturn(task);

        // Act
        Task createdTask = taskService.createTask(task);

        // Assert
        assertNotNull(createdTask);
        assertEquals(task.getTaskId(), createdTask.getTaskId());
    }

    @Test
    void testUpdateTask_Success() {
        // Arrange
        when(taskRepository.findById(task.getTaskId())).thenReturn(Optional.of(task));
        when(taskRepository.save(task)).thenReturn(task);

        // Act
        Task updatedTask = taskService.updateTask(task.getTaskId(), "Completed");

        // Assert
        assertNotNull(updatedTask);
        assertEquals("Completed", updatedTask.getStatus());
    }

    @Test
    void testUpdateTask_Failure() {
        // Arrange
        when(taskRepository.findById(task.getTaskId())).thenReturn(Optional.empty());

        // Act
        Task updatedTask = taskService.updateTask(task.getTaskId(), "Completed");

        // Assert
        assertNull(updatedTask);
    }

    @Test
    void testDeleteTask_Success() {
        // Arrange
        when(taskRepository.existsById(task.getTaskId())).thenReturn(true);

        // Act
        boolean isDeleted = taskService.deleteTask(task.getTaskId());

        // Assert
        assertTrue(isDeleted);
        verify(taskRepository, times(1)).deleteById(task.getTaskId());
    }

    @Test
    void testDeleteTask_Failure() {
        // Arrange
        when(taskRepository.existsById(task.getTaskId())).thenReturn(false);

        // Act
        boolean isDeleted = taskService.deleteTask(task.getTaskId());

        // Assert
        assertFalse(isDeleted);
    }

    @Test
    void testAssignTask_Success() {
        // Arrange
        when(taskRepository.findById(task.getTaskId())).thenReturn(Optional.of(task));
        when(taskRepository.save(task)).thenReturn(task);

        // Act
        Task assignedTask = taskService.assignTask(task.getTaskId(), "user456");

        // Assert
        assertNotNull(assignedTask);
        assertEquals("user456", assignedTask.getAssignedTo());
    }

    @Test
    void testAssignTask_Failure() {
        // Arrange
        when(taskRepository.findById(task.getTaskId())).thenReturn(Optional.empty());

        // Act
        Task assignedTask = taskService.assignTask(task.getTaskId(), "user456");

        // Assert
        assertNull(assignedTask);
    }
}