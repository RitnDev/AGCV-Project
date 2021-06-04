package com.ritndev.agcv.repository;

import com.ritndev.agcv.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ritn
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    public AppUser findByUserId(Long UserId);
    public AppUser findByUserName(String userName);
    public boolean existsByUserName(String userName);
    
}
