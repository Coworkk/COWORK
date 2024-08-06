package com.COWORK.COWORKING.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private LocalDateTime timeAttached;
    @ManyToOne
    private Project project;
    @ManyToOne
    private User user;
    private LocalDateTime timeUpdated;
}
