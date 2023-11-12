package com.yourcompany.taskapi.service;

import com.yourcompany.taskapi.model.Task;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
    @Service
    public class TaskService {
        private final AtomicLong counter = new AtomicLong();
        private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    public Task createTask(Task task) {
        long id = counter.incrementAndGet();
        task.setId(id);
        tasks.put(id, task);
        return task;
    }
    public Task getTask(Long id) {
        return tasks.get(id);
    }
    public Map<Long, Task> getAllTasks() {
        return tasks;
    }
    public Task updateTask(Long id, Task task) {
        task.setId(id);
        tasks.put(id, task);
        return task;
    }
    public void deleteTask(Long id) {
        tasks.remove(id);
    }
}
