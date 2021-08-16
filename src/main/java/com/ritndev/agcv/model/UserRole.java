package com.ritndev.agcv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
 
@Entity
@Table(name = "User_Role")
public class UserRole {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    @Getter @Setter private Long id;
    
    @JoinColumn(name = "User_Id", nullable = false)
    @Getter @Setter private Long userId;
    
    @JoinColumn(name = "Role_Id", nullable = false)
    @Getter @Setter private Long roleId;

    
    //Constructeur
    public UserRole() {}

    public UserRole(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
    
    
    
    
}