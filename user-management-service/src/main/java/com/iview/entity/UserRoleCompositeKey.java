package com.iview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleCompositeKey implements Serializable {

    @Column(name = "user_sk")
    private Long userId;

    @Column(name = "roles_sk")
    private String roleId;

}
