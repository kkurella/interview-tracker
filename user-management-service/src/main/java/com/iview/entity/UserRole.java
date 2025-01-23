package com.iview.entity;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
@Data
public class UserRole {

    @EmbeddedId
    private UserRoleCompositeKey id;

    @ManyToOne
    @MapsId("userSk")
    @JoinColumn(name = "user_sk")
    private User user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "roles_sk")
    private Role role;

    // Additional metadata (if needed)
//    private String assignedBy;
//    private LocalDateTime assignedAt;

    // Getters and setters
}
