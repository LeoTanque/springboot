package com.example.pincha.controller;

import com.example.pincha.models.TaskStatus;
import com.example.pincha.models.Tasks;
import com.example.pincha.repositories.TodoRepository;
import com.example.pincha.services.TaskDTO;
import com.example.pincha.services.TaskService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RequestMapping("/tareas")
@RestController
@Tag(name = "Tareas", description = "Endpoints para gestionar tareas")
public class TaskController {

  /*  @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public List<Tasks> getAllTodos() {
        return todoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Tasks getTodoById(@PathVariable Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
    }

    @PostMapping
    public Tasks createTodo(@RequestBody Tasks todo) {
        return todoRepository.save(todo);
    }

    @PutMapping("/{id}")
    public Tasks updateTodo(@PathVariable Long id, @RequestBody Tasks todoDetails) {
        Tasks todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        todo.setTitle(todoDetails.getTitle());
        todo.setDescription(todoDetails.getDescription());
        todo.setCompleted(todoDetails.isCompleted());
        return todoRepository.save(todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoRepository.deleteById(id);
    }*/


    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Tasks> getAllTasks() {
        return this.taskService.getAllTasks();
    }

    @GetMapping("/status/{status}")
    public List<Tasks> getAllByStatus(@PathVariable("status")TaskStatus status) {
        return this.taskService.findAllByStatus(status);
    }



    @PostMapping
    public Tasks createTask(@Valid @RequestBody TaskDTO taskDTO) {
        return this.taskService.createTask(taskDTO);
    }

    @PutMapping("/{id}")
    public Tasks updateTask(@PathVariable Long id, @Valid @RequestBody TaskDTO taskDTO) {
        return this.taskService.updateTask(id, taskDTO);
    }

    @PatchMapping("/completed/{id}")
    public ResponseEntity<Void> markTaskCompleted(@PathVariable("id") Long id){
        this.taskService.updateTaskAsFinished(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public Tasks getTaskById(@PathVariable("id") Long id) {
        return this.taskService.getTaskById(id);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id){
        this.taskService.deleteTask(id);
        return ResponseEntity.noContent().build();

    }


}
