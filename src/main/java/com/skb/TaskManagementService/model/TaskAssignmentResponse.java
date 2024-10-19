package com.skb.TaskManagementService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskAssignmentResponse {
    private Long taskId;    // ID! - Non-nullable
    private String assignedTo; // String! - Non-nullable
}