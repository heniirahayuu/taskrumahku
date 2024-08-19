package com.example.taskrumahku.repository;

import com.example.taskrumahku.model.Mtask;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MtaskRepository extends JpaRepository<Mtask, Long> {
    List<Mtask> findByStatus(String status);
}
