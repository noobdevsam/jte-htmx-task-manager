package com.example.jte_htmx_task_manager;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
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
