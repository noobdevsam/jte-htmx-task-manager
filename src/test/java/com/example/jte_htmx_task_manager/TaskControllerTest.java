package com.example.jte_htmx_task_manager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(TaskRepository.class)
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_index() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("tasks"));
    }

    @Test
    void test_addTask() throws Exception {
        var task_description = "New Test Task";
        var result = mockMvc.perform(post("add-task")
                .param("description", task_description))
                .andExpect(status().isOk())
                .andExpect(view().name("task-row"))
                .andReturn();
        var addedTask = (Task) result.getModelAndView().getModel()
                .get("task");
        assertNotNull(task_description, addedTask.getDescription());
    }

    @Test
    void test_deleteTask() throws Exception {
        // first, add a task
        var addResult = mockMvc.perform(post("/add-task")
                    .param("description", "task to be deleted."))
                .andExpect(status().isOk()).andReturn();

        var addedTask = (Task) addResult.getModelAndView().getModel().get("task");

        //delete the task
        mockMvc.perform(
                delete("/delete-tasl" + addedTask.getId())
        ).andExpect(status().isOk());

        //delete the same task
        mockMvc.perform(
                delete("/delete-tasl" + addedTask.getId())
        ).andExpect(status().isOk());
    }
}












