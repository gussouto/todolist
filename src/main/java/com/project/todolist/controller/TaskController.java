package com.project.todolist.controller;

import com.project.todolist.dto.TaskDTO;
import com.project.todolist.entity.TaskEntity;
import com.project.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/tasks")
public class TaskController {

    private static final String REDIRECT_TASK = "redirect:/tasks/listTask";


    @Autowired
    private TaskService taskService;

    // Página para mostrar todas as tarefas
    @GetMapping("/listTask")
    public ModelAndView listTasks() {
        ModelAndView mv = new ModelAndView("list_task");
        mv.addObject("listTasks", taskService.getAllTasks());
        return mv;
    }

/*    @GetMapping("/listTask")
    public ModelAndView listTasks() {
        List<TaskEntity> tasks = taskService.getAllTasks();
        ModelAndView mv = new ModelAndView("list_task");
        mv.addObject("listTasks", tasks);
        return mv;
    }*/

    // Página para criar uma nova tarefas
    @GetMapping("/create")
    public ModelAndView showCreateTaskForm() {
        ModelAndView mv = new ModelAndView("create_task");
        mv.addObject("task", new TaskEntity());
        return mv;
    }

    // Metodo para processar o formulário de criação de uma nova tarefa
    @PostMapping("/create")
    public ModelAndView createTask(@ModelAttribute("task") TaskDTO task) {
        taskService.saveTask(task);
        return new ModelAndView(REDIRECT_TASK); // Redirecionando para lista de tarefas
    }

    //Página para editar uma tarefa existente
    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("edit_task");
        mv.addObject("editTask", taskService.getTaskById(id));
        return mv;
    }

    // Metodo para processar a atualização da tarefa
    @PostMapping("/edit/{id}")
    public ModelAndView editTask(@PathVariable Long id, @ModelAttribute("task") TaskDTO task) {
        taskService.saveTask(task);
        return new ModelAndView(REDIRECT_TASK); // Redirecionando para lista de tarefas
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ModelAndView(REDIRECT_TASK); // Redirecionando para lista de tarefas
    }

}


