package com.ritndev.agcv.repository;

import com.ritndev.agcv.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ritn
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    
    public UserRole findByUserId(Long userId);
    
}
