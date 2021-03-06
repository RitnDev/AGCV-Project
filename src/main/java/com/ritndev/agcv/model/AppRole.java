package com.ritndev.agcv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
 
@Entity
@Table(name = "APP_ROLE")
public class AppRole {
     
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROLE_ID", nullable = false)
    @Getter @Setter private Long roleId;
 
    @Column(name = "ROLE_NAME", length = 30, nullable = false)
    @Getter @Setter private String roleName;

    
    //Constructeur
    public AppRole() {}

    public AppRole(String roleName) {
        this.roleName = roleName;
    }
    
    
    
      
}
