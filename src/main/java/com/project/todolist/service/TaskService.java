package com.project.todolist.service;

import com.project.todolist.dto.TaskDTO;
import com.project.todolist.entity.TaskEntity;
import com.project.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    public TaskEntity getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found!"));
    }

    public TaskEntity saveTask(TaskDTO taskDTO) {
        return taskRepository.save(new TaskEntity(taskDTO.getTask(), taskDTO.getDescription(),
                taskDTO.getDate(), taskDTO.isCompleted()));
    }

    public TaskEntity updateTask(Long id, TaskDTO taskDTO) {

        // Verificar se a task existe
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found!"));

        // Atualizar as propriedades
        taskEntity.setTask(taskDTO.getTask());
        taskEntity.setDescription(taskDTO.getDescription());
        taskEntity.setDate(taskDTO.getDate());
        taskEntity.setCompleted(taskDTO.isCompleted());

        // Salva as alterações no banco de dados
        return taskRepository.save(taskEntity);
    }


    public LocalDate date() {
        return LocalDate.now();
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}
