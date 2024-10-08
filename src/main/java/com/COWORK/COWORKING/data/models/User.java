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
    private  String userId;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
}
