package com.example.taskrumahku.service;

import com.example.taskrumahku.model.Mtask;
import com.example.taskrumahku.repository.MtaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MtaskServiceTest {

    @Autowired
    private MtaskRepository mtaskRepository;

    public Mtask createMtask(Mtask mtask) {
        return mtaskRepository.save(mtask);
    }

    public Mtask updateMtask(Long id, Mtask mtask) {
        Optional<Mtask> existingMtask = mtaskRepository.findById(id);
        if (existingMtask.isPresent()) {
            Mtask updatedMtask = existingMtask.get();
            updatedMtask.setName(mtask.getName());
            updatedMtask.setStatus(mtask.getStatus());
            return mtaskRepository.save(updatedMtask);
        }
        return null;
    }

    public List<Mtask> getAllMtasks() {
        return mtaskRepository.findAll();
    }

    public List<Mtask> getMtasksByStatus(String status) {
        return mtaskRepository.findByStatus(status);
    }

    public void deleteMtask(Long id) {
        mtaskRepository.deleteById(id);
    }
}
