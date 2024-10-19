package com.skb.TaskManagementService.resolver;

import com.skb.TaskManagementService.model.DeleteResponse;
import com.skb.TaskManagementService.model.Task;
import com.skb.TaskManagementService.model.TaskAssignmentResponse;
import com.skb.TaskManagementService.model.TaskResponse;
import com.skb.TaskManagementService.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TaskResolver {
    @Autowired
    private TaskService taskService;

    // Query to retrieve all tasks
    @QueryMapping
    public List<Task> tasks() {
        return taskService.getAllTasks();
    }

    // Mutation to create a new task
    @MutationMapping
    public TaskResponse createTask(@Argument String taskName, @Argument String description, @Argument String dueDate) {
        Task newTask = new Task(null, taskName, description, "Pending", null, dueDate);
        Task savedTask = taskService.createTask(newTask);
        return new TaskResponse(savedTask.getTaskId(), savedTask.getStatus());
    }

    // Mutation to update an existing task's status
    @MutationMapping
    public TaskResponse updateTask(@Argument Long taskId, @Argument String status) {
        Task updatedTask = taskService.updateTask(taskId, status);
        if (updatedTask != null) {
            return new TaskResponse(updatedTask.getTaskId(), updatedTask.getStatus());
        }
        return null;
    }

    // Mutation to delete a task by taskId
    @MutationMapping
    public DeleteResponse deleteTask(@Argument Long taskId) {
        boolean success = taskService.deleteTask(taskId);
        return new DeleteResponse(success);
    }

    // Mutation to assign a task to a user
    @MutationMapping
    public TaskAssignmentResponse assignTask(@Argument Long taskId, @Argument String userId) {
        Task assignedTask = taskService.assignTask(taskId, userId);
        if (assignedTask != null) {
            return new TaskAssignmentResponse(assignedTask.getTaskId(), assignedTask.getAssignedTo());
        }
        return null;
    }
}
