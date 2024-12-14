package com.example.pincha.repositories;

import com.example.pincha.models.TaskStatus;
import com.example.pincha.models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends JpaRepository<Tasks, Long> {

    public List<Tasks>findAllByTaskStatus(TaskStatus status);


    @Modifying
    @Query(value = "UPDATE TASK SET COMPLETED= true WHERE ID=:id", nativeQuery = true)
    public void markTaskFinished(@Param("id") Long id);

}
