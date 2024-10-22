package com.example.jte_htmx_task_manager;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class JteHtmxTaskManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JteHtmxTaskManagerApplication.class, args);
	}

}

class Task{
	private final String id;
	private final String description;

	public Task(String description) {
		this.id = UUID.randomUUID().toString();
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
}

@Repository
class TaskRepository {
	private final List<Task> tasks = new ArrayList<>();

	public TaskRepository() {
	}

	public List<Task> findAll() {
		return tasks;
	}

	public void create(Task task) {
		tasks.add(task);
	}

	public boolean remove(String id) {
		return tasks.removeIf(task -> task.getId().equals(id));
	}

	@PostConstruct
	private void init() {
		tasks.addAll(
				List.of(
					new Task("Task A"),
					new Task("Task B"),
					new Task("Task C"),
					new Task("Task D"),
					new Task("Task E"),
					new Task("Task F"),
					new Task("Task G"),
					new Task("Task H"),
					new Task("Task I")
				)
		);
	}
}

class TaskController{

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
		return "task-row";
	}

	@DeleteMapping("delete-task/{id}")
	@ResponseBody
	public void deleteTask(@PathVariable String id) {
		var removed = repository.remove(id);
		log.info("Task with id {} was deleted", id);
	}

}


















