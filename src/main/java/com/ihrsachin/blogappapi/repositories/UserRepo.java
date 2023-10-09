package com.ihrsachin.blogappapi.repositories;

import com.ihrsachin.blogappapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

}
