package com.pascal7.ingre_api_mono.repository;

import com.pascal7.ingre_api_mono.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
}
