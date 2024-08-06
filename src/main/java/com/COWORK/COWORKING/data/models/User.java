package com.COWORK.COWORKING.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Project> project;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
}
