package com.yourcompany.taskapi.config;

import com.yourcompany.taskapi.model.Task;
import com.yourcompany.taskapi.model.TaskStatus;
import com.yourcompany.taskapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.Random;

@Component
public class TestDataLoader implements CommandLineRunner {
    private final TaskService taskService;
    private static final Random SEEDER = new Random(12345);
    @Autowired
    public TestDataLoader(TaskService taskService) {
        this.taskService = taskService;
    }
    @Override
    public void run(String... args) throws Exception {
        loadTestData();
    }
    private void loadTestData() {
        for (int i = 1; i <= 10; i++) {
            Task task = new Task();
            task.setTitle("Task " + i);
            task.setDescription("Description for task " + i);
            task.setDueDate(LocalDate.now().plusDays(SEEDER.nextInt(10) + 1)); // random due date within 10 days
            task.setStatus(TaskStatus.valueOf(SEEDER.nextBoolean() ? "NEW" : "COMPLETED")); // random status
            taskService.createTask(task);
        }
    }
}

