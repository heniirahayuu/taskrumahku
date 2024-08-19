package com.example.taskrumahku.controller;

import com.example.taskrumahku.model.Mtask;
import com.example.taskrumahku.service.MtaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class MtaskController {

    private final MtaskService mtaskService;

    @Autowired
    public MtaskController(MtaskService mtaskService) {
        this.mtaskService = mtaskService;
    }

    // edpuntuk membuat task baru
    @PostMapping
    public ResponseEntity<Mtask> createTask(@RequestBody Mtask task) {
        Mtask createdTask = mtaskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    // edp untuk memperbarui task berdasarkan ID
    @PutMapping("/{id}")
    public ResponseEntity<Mtask> updateTask(@PathVariable Long id, @RequestBody Mtask taskDetails) {
        Mtask updatedTask = mtaskService.updateTask(id, taskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    // edp untuk mendapatkan semua task
    @GetMapping
    public ResponseEntity<List<Mtask>> getAllTasks() {
        List<Mtask> tasks = mtaskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    // edp untuk mendapatkan task berdasarkan status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Mtask>> getTasksByStatus(@PathVariable String status) {
        List<Mtask> tasks = mtaskService.getTasksByStatus(status);
        return ResponseEntity.ok(tasks);
    }
}
