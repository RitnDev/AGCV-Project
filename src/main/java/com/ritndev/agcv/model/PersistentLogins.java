package com.ritndev.agcv.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
 


/**
 *
 * @author Ritn
 */
@Entity
@Table(name = "persistent_logins")
public class PersistentLogins {
    
    /*
    username VARCHAR(254) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
    */
    
    @Id
    @Column(name = "series", nullable = false)
    @Getter @Setter private String series;
    
    @Column(name = "last_used", nullable = false)
    @Getter @Setter private Timestamp last_used;
    
    @Column(name = "username", nullable = false)
    @Getter @Setter private String username;
    
    @Column(name = "token", nullable = false)
    @Getter @Setter private String token;
     
    
}
