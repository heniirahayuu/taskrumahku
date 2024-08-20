package com.example.taskrumahku.repository;

import com.example.taskrumahku.model.Mtask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MtaskRepository extends JpaRepository<Mtask, Long> {
    List<Mtask> findByStatus(String status);
}
