package org.pollbox.poll.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "AUTHORITIES")
public class Authority {
    private Long id;
    
    @NotEmpty
    @Size(max=50)
    private String username;
    
    @NotEmpty
    @Size(max=50)
    private String authority;

    // Default constructor
    public Authority() {
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "USERNAME", nullable = false, length = 50)
    @Index(name="USERNAME_INX")
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "AUTHORITY", nullable = false, length = 50)
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
    
    public String toString() {
        return "Authority info: " 
            + "\n\tAuthority: " + getAuthority()
            + "\n\tUsername:  " + getUsername();
    }
}
