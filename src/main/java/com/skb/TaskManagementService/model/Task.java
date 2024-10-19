package com.skb.TaskManagementService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;      // Changed from String to Long

    @Column(nullable = false)
    private String taskName;   // Non-nullable field

    private String description; // Nullable field

    @Column(nullable = false)
    private String status;      // Non-nullable field

    private String assignedTo;  // Nullable field

    private String dueDate;     // Nullable field

}
