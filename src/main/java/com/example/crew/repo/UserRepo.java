package com.example.crew.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.crew.model.User;
public interface UserRepo extends JpaRepository<User,Long>{
    User findByUsername(String username);
}
