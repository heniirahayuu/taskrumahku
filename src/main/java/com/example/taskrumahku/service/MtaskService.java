package com.example.taskrumahku.service;

import com.example.taskrumahku.exception.ResourceNotFoundException;
import com.example.taskrumahku.model.Mtask;
import com.example.taskrumahku.repository.MtaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MtaskService {

    private final MtaskRepository mtaskRepository;

    @Autowired
    public MtaskService(MtaskRepository mtaskRepository) {
        this.mtaskRepository = mtaskRepository;
    }

    // create task baru
    public Mtask createTask(Mtask task) {
        return mtaskRepository.save(task);
    }

    // get task yang sudah ada
    public Mtask updateTask(Long id, Mtask taskDetails) {
        Mtask task = mtaskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task tidak ditemukan dengan id: " + id));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        return mtaskRepository.save(task);
    }

    // Mengambil semua task
    public List<Mtask> getAllTasks() {
        return mtaskRepository.findAll();
    }

    // Mengambil task berdasarkan status
    public List<Mtask> getTasksByStatus(String status) {
        return mtaskRepository.findByStatus(status);
    }
}
