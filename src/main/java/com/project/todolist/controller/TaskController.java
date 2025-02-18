package com.project.todolist.controller;

import com.project.todolist.dto.TaskDTO;
import com.project.todolist.entity.TaskEntity;
import com.project.todolist.repository.TaskRepository;
import com.project.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/tasks")
public class TaskController {

    private static final String REDIRECT_TASK = "redirect:/tasks/listTask";


    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    // Página para mostrar todas as tarefas
    @GetMapping("/listTask")
    public ModelAndView listTasks() {
        ModelAndView mv = new ModelAndView("list_task");
        mv.addObject("listTasks", taskService.getAllTasks());
        return mv;
    }

    // Página para criar uma nova tarefas
    @GetMapping("/create")
    public ModelAndView showCreateTaskForm() {
        ModelAndView mv = new ModelAndView("create_task");
        mv.addObject("task", new TaskEntity());
        return mv;
    }

    // Metodo para processar o formulário de criação de uma nova tarefa
    @PostMapping("/create")
    public ModelAndView createTask(@ModelAttribute("task") TaskDTO taskDTO) {
        taskService.saveTask(taskDTO);
        return new ModelAndView(REDIRECT_TASK); // Redirecionando para lista de tarefas
    }

    // Form para mostrar a tarefa selecionada pelo id
    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("task", taskService.getTaskById(id));
        return "update_task";
    }

/*
    Outra maneira de se mostrar o Form da task escolhida. (Mantido no código apenas para fins educativos)
    @GetMapping("/{id}/edit")
    public ModelAndView showUpdateForm(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("update_task");
        mv.addObject("task", taskService.getTaskById(id));
        return mv;
    }
*/

    // Metodo para editar tarefa da lista
        // Usar RequestBody para requisições POST via JSON
        // Usar ModelAttribute quando estiver trabalhando com Thymeleaf e formulários HTML
    @PostMapping("/{id}")
    public String updateTask(@PathVariable("id") Long id, @ModelAttribute("task") TaskDTO taskDTO) {
        taskService.updateTask(id, taskDTO);
        return REDIRECT_TASK;
    }

    // Metodo para deletar tarefa da lista
    @GetMapping("/delete/{id}")
    public ModelAndView deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return new ModelAndView(REDIRECT_TASK); // Redirecionando para lista de tarefas
    }

}


