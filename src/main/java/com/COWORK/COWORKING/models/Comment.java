package com.COWORK.COWORKING.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @ManyToOne
    private SubTask subTask;
    @ManyToOne
    private Task task;
    @ManyToOne
    private User commenter;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;
}
