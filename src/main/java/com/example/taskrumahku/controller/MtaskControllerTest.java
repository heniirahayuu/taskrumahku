package com.example.taskrumahku.controller;

import com.example.taskrumahku.model.Mtask;
import com.example.taskrumahku.service.MtaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(MtaskController.class)
public class MtaskControllerTest {

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
    void testCreateMtask() throws Exception {
        Mtask mtask = new Mtask();
        mtask.setName("Test Mtask");
        mtask.setStatus("Pending");

        when(mtaskService.createMtask(any(Mtask.class))).thenReturn(mtask);

        mockMvc.perform(post("/mtasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test Mtask\", \"status\":\"Pending\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Mtask"))
                .andExpect(jsonPath("$.status").value("Pending"));
    }
}
