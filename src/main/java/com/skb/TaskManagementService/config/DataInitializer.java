package com.skb.TaskManagementService.config;

import com.skb.TaskManagementService.model.Task;
import com.skb.TaskManagementService.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {
        taskRepository.save(new Task(null, "Task 1", "Description for Task 1", "Pending", null, "2024-10-20"));
        taskRepository.save(new Task(null, "Task 2", "Description for Task 2", "In Progress", "user1", "2024-10-21"));
        taskRepository.save(new Task(null, "Task 3", "Description for Task 3", "Completed", "user2", "2024-10-22"));
        taskRepository.save(new Task(null, "Task 4", "Description for Task 4", "Pending", "user3", "2024-10-23"));

        System.out.println("Dummy tasks have been inserted.");
    }
}