package com.COWORK.COWORKING.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static com.COWORK.COWORKING.data.models.RoleName.*;
import static jakarta.persistence.FetchType.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
//@NamedQueries({
//        @NamedQuery(name = "ProjectUserRole.findByRole", query = "select p from ProjectUserRole p where p.role = :Role")
//})
public class ProjectUserRole {
    @Id
    @GeneratedValue
    private Long id;
    @JoinColumn(name = "project_id")
    @ManyToOne(fetch = LAZY , optional = false)
    private Project project;
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = LAZY , optional = false)
    private User user;
    @JoinColumn(name = "role_id")
    @ManyToOne(fetch = EAGER)
    private Role role;
}
