package com.example.taskrumahku.service;

import com.example.taskrumahku.exception.ResourceNotFoundException;
import com.example.taskrumahku.model.Mtask;
import com.example.taskrumahku.repository.MtaskRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class MtaskServiceTest {

    @Mock
    private MtaskRepository mtaskRepository;

    @InjectMocks
    private MtaskService mtaskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTask() {
        Mtask task = new Mtask("Test Title", "Test Description", "Pending");
        when(mtaskRepository.save(any(Mtask.class))).thenReturn(task);

        Mtask createdTask = mtaskService.createTask(task);

        assertEquals("Test Title", createdTask.getTitle());
        assertEquals("Test Description", createdTask.getDescription());
        assertEquals("Pending", createdTask.getStatus());
    }

    @Test
    void testUpdateTask() {
        Mtask existingTask = new Mtask("Old Title", "Old Description", "Pending");
        Mtask updatedTask = new Mtask("New Title", "New Description", "Completed");

        when(mtaskRepository.findById(anyLong())).thenReturn(Optional.of(existingTask));
        when(mtaskRepository.save(any(Mtask.class))).thenReturn(updatedTask);

        Mtask result = mtaskService.updateTask(1L, updatedTask);

        assertEquals("New Title", result.getTitle());
        assertEquals("New Description", result.getDescription());
        assertEquals("Completed", result.getStatus());
    }

    @Test
    void testUpdateTaskThrowsResourceNotFoundException() {
        when(mtaskRepository.findById(anyLong())).thenReturn(Optional.empty());

        Mtask updatedTask = new Mtask("New Title", "New Description", "Completed");

        assertThrows(ResourceNotFoundException.class, () -> mtaskService.updateTask(1L, updatedTask));
    }

    @Test
    void testGetAllTasks() {
        Mtask task1 = new Mtask("Title1", "Description1", "Pending");
        Mtask task2 = new Mtask("Title2", "Description2", "Completed");
        List<Mtask> tasks = Arrays.asList(task1, task2);

        when(mtaskRepository.findAll()).thenReturn(tasks);

        List<Mtask> result = mtaskService.getAllTasks();

        assertEquals(2, result.size());
        assertEquals("Title1", result.get(0).getTitle());
        assertEquals("Title2", result.get(1).getTitle());
    }

    @Test
    void testGetTasksByStatus() {
        Mtask task1 = new Mtask("Title1", "Description1", "Pending");
        List<Mtask> tasks = Arrays.asList(task1);

        when(mtaskRepository.findByStatus(anyString())).thenReturn(tasks);

        List<Mtask> result = mtaskService.getTasksByStatus("Pending");

        assertEquals(1, result.size());
        assertEquals("Title1", result.get(0).getTitle());
    }
}
