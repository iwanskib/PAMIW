package com.yourcompany.taskapi.controller;

import com.yourcompany.taskapi.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
public class TaskViewController {
    private final RestTemplate restTemplate;
    private final String apiUrl = "http://localhost:8080/api/newentity";

    @Autowired
    public TaskViewController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/tasks")
    public String showTasks(Model model) {
        List<Task> tasks = Arrays.asList(restTemplate.getForObject(apiUrl, Task[].class));
        model.addAttribute("tasks", tasks);
        return "tasks";
    }
}
