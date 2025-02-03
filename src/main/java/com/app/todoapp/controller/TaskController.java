package com.app.todoapp.controller;

import com.app.todoapp.models.Task;
import com.app.todoapp.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

//    @GetMapping("/tasks")
//    public String getTasks(Model model){
//        List<Task> tasks = taskService.getAllTasks();
//        model.addAttribute("tasks", tasks);
//        return "tasks";
//    }
@GetMapping("/tasks")
public String getTasks(Model model) {
    List<Task> tasks = taskService.getAllTasks();

    if (tasks == null || tasks.isEmpty()) {
        // Log this information to see if tasks are actually fetched from the DB
        System.out.println("No tasks available");
    } else {
        System.out.println("Tasks: " + tasks);
    }

    model.addAttribute("tasks", tasks);
    return "tasks";
}

@PostMapping
    public String createTask(@RequestParam String title){
        taskService.createTask(title);
        return "redirect:/tasks";
}

@GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    return "redirect:/tasks";
}

    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id){
        taskService.toggleTask(id);
        return "redirect:/tasks";
    }


}
