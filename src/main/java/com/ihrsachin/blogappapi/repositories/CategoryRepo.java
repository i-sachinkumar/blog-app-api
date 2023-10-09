package com.ihrsachin.blogappapi.repositories;

import com.ihrsachin.blogappapi.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
