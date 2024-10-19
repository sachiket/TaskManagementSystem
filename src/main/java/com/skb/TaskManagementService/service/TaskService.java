package com.skb.TaskManagementService.service;

import com.skb.TaskManagementService.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    Task createTask(Task task);

    Task updateTask(Long taskId, String status);

    boolean deleteTask(Long taskId);

    Task assignTask(Long taskId, String userId);
}