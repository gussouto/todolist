package com.project.todolist.controller;

import com.project.todolist.model.TaskEntity;
import com.project.todolist.repository.TaskRepository;
import com.project.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskService service;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/registerTask")
    public ModelAndView register(TaskEntity task) {
        ModelAndView mv = new ModelAndView("admin/task/register");
        mv.addObject("task", task);
        return mv;
    }

    @GetMapping("/allTasks")
    public List<TaskEntity> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/getTaskById/{id}")
    public TaskEntity getTaskById(@PathVariable("id") Long id) {
        return service.getTaskById(id);
    }

    @PostMapping("/saveTask")
    public TaskEntity saveTask(@RequestBody TaskEntity task) {
        return service.saveTask(task);
    }

    @GetMapping("/editTask/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        return register(taskRepository.getReferenceById(id));
    }

    @DeleteMapping("/deleteTask/{id}")
    public void deleteTask(@PathVariable("id") Long id) {
        service.deleteTask(id);
    }

}
