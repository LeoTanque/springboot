package com.example.pincha.services;

import com.example.pincha.exeptions.ToDoExceptions;
import com.example.pincha.mapper.TaskInDtoToTask;
import com.example.pincha.models.TaskStatus;
import com.example.pincha.models.Tasks;
import com.example.pincha.repositories.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TodoRepository todoRepository;

    private TaskInDtoToTask mapper;

    public TaskService(TodoRepository todoRepository, TaskInDtoToTask mapper) {
        this.todoRepository = todoRepository;
        this.mapper = mapper;
    }

    public List<Tasks> getAllTasks() {
        return todoRepository.findAll();
    }



    public Tasks createTask(TaskDTO taskDTO) {
        Tasks task = mapper.map(taskDTO);
         return this.todoRepository.save(task);
    }

    public Tasks updateTask(Long id, TaskDTO taskDTO) {
        Tasks task = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setEta(taskDTO.getEta());
        task.setCreatedDate(LocalDateTime.now());
        task.setCompleted(false);
        task.setTaskStatus(TaskStatus.ON_TIME);
        return todoRepository.save(task);
    }



    public List<Tasks>findAllByStatus(TaskStatus status){
        return this.todoRepository.findAllByTaskStatus(status);
    }

    @Transactional
    public void updateTaskAsFinished(Long id){
       Optional<Tasks> optionalTasks =this.todoRepository.findById(id);
       if (optionalTasks.isEmpty()){
           throw new ToDoExceptions("Tarea no encontrada", HttpStatus.NOT_FOUND);
       }
        this.todoRepository.markTaskFinished(id);
    }

    public Tasks getTaskById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ToDoExceptions("Tarea no encontrada", HttpStatus.NOT_FOUND));
    }

    public void deleteTask(Long id) {
        Optional<Tasks> optionalTasks =this.todoRepository.findById(id);
        if (optionalTasks.isEmpty()){
            throw new ToDoExceptions("Tarea no encontrada", HttpStatus.NOT_FOUND);
        }

        this.todoRepository.deleteById(id);
    }
}
