package com.iview.repository;

import com.iview.entity.UserRole;
import com.iview.entity.UserRoleCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleCompositeKey> {

}
