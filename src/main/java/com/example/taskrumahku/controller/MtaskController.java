package com.example.taskrumahku.controller;

import com.example.taskrumahku.model.Mtask;
import com.example.taskrumahku.service.MtaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class MtaskController {

    private final MtaskService mtaskService;

    @Autowired
    public MtaskController(MtaskService mtaskService) {
        this.mtaskService = mtaskService;
    }

    // @PostMapping
    // public ResponseEntity<Mtask> createTask(@RequestBody Mtask task) {
    //     return ResponseEntity.ok(mtaskService.createTask(task));
    // }

    @PostMapping
    public ResponseEntity<Mtask> createTask(@RequestBody Mtask task) {
        Mtask createdTask = mtaskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mtask> updateTask(@PathVariable Long id, @RequestBody Mtask taskDetails) {
        return ResponseEntity.ok(mtaskService.updateTask(id, taskDetails));
    }

    @GetMapping
    public ResponseEntity<List<Mtask>> getAllTasks() {
        return ResponseEntity.ok(mtaskService.getAllTasks());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Mtask>> getTasksByStatus(@PathVariable String status) {
        return ResponseEntity.ok(mtaskService.getTasksByStatus(status));
    }
}
