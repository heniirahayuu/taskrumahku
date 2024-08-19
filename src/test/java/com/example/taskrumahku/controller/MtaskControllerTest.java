package com.example.taskrumahku.controller;

import com.example.taskrumahku.model.Mtask;
import com.example.taskrumahku.service.MtaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(MtaskController.class)
public class MtaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MtaskService mtaskService;

    @InjectMocks
    private MtaskController mtaskController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mtaskController).build();
    }

    @Test
    void testCreateTask() throws Exception {
        Mtask task = new Mtask("Test Title", "Test Description", "Pending");
        when(mtaskService.createTask(any(Mtask.class))).thenReturn(task);

        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(task)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.status").value("Pending"));
    }

    @Test
    void testUpdateTask() throws Exception {
        Mtask task = new Mtask("Updated Title", "Updated Description", "Completed");
        when(mtaskService.updateTask(anyLong(), any(Mtask.class))).thenReturn(task);

        mockMvc.perform(put("/api/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.description").value("Updated Description"))
                .andExpect(jsonPath("$.status").value("Completed"));
    }

    @Test
    void testGetAllTasks() throws Exception {
        Mtask task1 = new Mtask("Title1", "Description1", "Pending");
        Mtask task2 = new Mtask("Title2", "Description2", "Completed");
        List<Mtask> tasks = Arrays.asList(task1, task2);

        when(mtaskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(get("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Title1"))
                .andExpect(jsonPath("$[1].title").value("Title2"));
    }

    @Test
    void testGetTasksByStatus() throws Exception {
        Mtask task1 = new Mtask("Title1", "Description1", "Pending");
        List<Mtask> tasks = Arrays.asList(task1);

        when(mtaskService.getTasksByStatus(anyString())).thenReturn(tasks);

        mockMvc.perform(get("/api/tasks/status/Pending")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Title1"))
                .andExpect(jsonPath("$[0].status").value("Pending"));
    }

    private static String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
