package com.example.pincha.mapper;

import com.example.pincha.models.TaskStatus;
import com.example.pincha.models.Tasks;
import com.example.pincha.services.TaskDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class TaskInDtoToTask implements IMapper<TaskDTO, Tasks>{
    @Override
    public Tasks map(TaskDTO in) {

        Tasks task = new Tasks();
        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setEta(in.getEta());
        task.setCreatedDate(LocalDateTime.now());
        task.setCompleted(false);
        task.setTaskStatus(TaskStatus.ON_TIME);
        return task;
    }
}
