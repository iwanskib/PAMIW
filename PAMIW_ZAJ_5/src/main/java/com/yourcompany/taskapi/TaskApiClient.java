package com.yourcompany.taskapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yourcompany.taskapi.model.Task;
import com.yourcompany.taskapi.model.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

public class TaskApiClient {

    private final String baseUrl = "http://localhost:8080/api/newentity";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;

    @Autowired
    public TaskApiClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void createTask(Task task) throws Exception {
        String requestBody = objectMapper.writeValueAsString(task);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    }

    // Metoda do pobierania zadania
    public void getTask(Long taskId) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/" + taskId))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    }

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        TaskApiClient client = new TaskApiClient(objectMapper);
        try {
            Task newTask = new Task();
            newTask.setTitle("New Task Title");
            newTask.setDescription("New Task Description");
            newTask.setDueDate(LocalDate.now().plusDays(5));
            newTask.setStatus(TaskStatus.NEW);

            client.createTask(newTask);
            client.getTask(1L); // Załóżmy, że chcemy pobrać zadanie o ID 1
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
