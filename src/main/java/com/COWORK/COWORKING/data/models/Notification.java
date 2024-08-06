package com.COWORK.COWORKING.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue
    private Long notificationId;
    @ManyToOne
    private User user;
    private LocalDateTime notificationDate;
    private LocalDateTime dateUpdated;
}
