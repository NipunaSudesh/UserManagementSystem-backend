package com.codemy.backdev.repository;

import com.codemy.backdev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}




