package com.COWORK.COWORKING.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
    @ManyToOne
    private Project project;
    @ManyToOne
    private User user;
    private Priority priority;
}
