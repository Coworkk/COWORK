package com.COWORK.COWORKING.data.models;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.AUTO;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private RoleName roleName;
}
