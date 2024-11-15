package com.example.jte_htmx_task_manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String index(Model m) {
        m.addAttribute("tasks", repository.findAll());
        return "index";
    }

    @PostMapping("/add-task")
    public String addTask(@RequestParam String description, Model m) {
        var newTask = new Task(description);
        repository.create(newTask);
        m.addAttribute("task", newTask);
        log.info("Task with id=({}) was created", newTask.getId());
        return "task-row";
    }

    @DeleteMapping("delete-task/{id}")
    @ResponseBody
    public void deleteTask(@PathVariable String id) {
        var removed = repository.remove(id);
        log.info("Task with id=({}) was deleted", id);
    }

}
