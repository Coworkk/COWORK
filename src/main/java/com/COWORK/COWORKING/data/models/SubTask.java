package com.COWORK.COWORKING.data.models;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subtasks")
public class SubTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subTaskId;
    private String title;
    private String description;
    private Status status;

    @Setter(AccessLevel.NONE)
    @JsonSerialize(using = JsonSerializer.class)
    @JsonDeserialize(using = JsonDeserializer.class)
    private LocalDateTime dateCreated;

    @Setter(AccessLevel.NONE)
    @JsonSerialize(using = JsonSerializer.class)
    @JsonDeserialize(using = JsonDeserializer.class)
    private LocalDateTime dateUpdated;

    @JsonSerialize(using = JsonSerializer.class)
    @JsonDeserialize(using = JsonDeserializer.class)
    private LocalDateTime startDate;

    @JsonSerialize(using = JsonSerializer.class)
    @JsonDeserialize(using = JsonDeserializer.class)
    private LocalDateTime dueDate;
    @ManyToOne
    private Task task;

    @PrePersist
    public void setDateCreated() {
        this.dateCreated = now();
    }

    @PreUpdate
    public void setDateUpdated() {
        this.dateUpdated = now();
    }
}
