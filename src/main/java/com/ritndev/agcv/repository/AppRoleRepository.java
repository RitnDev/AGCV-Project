package com.ritndev.agcv.repository;

import com.ritndev.agcv.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ritn
 */
@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    
    public AppRole findByRoleId(Long roleId);
    public AppRole findByRoleName(String roleName);
    
}
