package com.example.crew.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
@Entity
@Table
public class User {
    
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
    
    @NotBlank(message = "UserName is mandatory")
   private String username;
   @NotBlank(message = "Email is mandatory")
   private String email;
   @NotBlank(message="Password is mandatory")
   private String password;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + "]";
    }
    public User(Long id, @NotBlank(message = "UserName is mandatory") String username,
            @NotBlank(message = "Email is mandatory") String email,
            @NotBlank(message = "Password is mandatory") String password) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public User() {
        
    }
}
