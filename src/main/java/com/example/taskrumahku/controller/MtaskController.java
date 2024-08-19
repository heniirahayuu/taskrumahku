package com.example.taskrumahku.controller;

import com.example.taskrumahku.model.Mtask;
import com.example.taskrumahku.service.MtaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mtasks")
public class MtaskController {

    @Autowired
    private MtaskService mtaskService;

    @PostMapping
    public ResponseEntity<Mtask> createMtask(@RequestBody Mtask mtask) {
        return new ResponseEntity<>(mtaskService.createMtask(mtask), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mtask> updateMtask(@PathVariable Long id, @RequestBody Mtask mtask) {
        Mtask updatedMtask = mtaskService.updateMtask(id, mtask);
        if (updatedMtask != null) {
            return new ResponseEntity<>(updatedMtask, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Mtask>> getAllMtasks() {
        return new ResponseEntity<>(mtaskService.getAllMtasks(), HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Mtask>> getMtasksByStatus(@PathVariable String status) {
        return new ResponseEntity<>(mtaskService.getMtasksByStatus(status), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMtask(@PathVariable Long id) {
        mtaskService.deleteMtask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
