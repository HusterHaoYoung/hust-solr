package com.hust.hustsearch.dao;

import com.hust.hustsearch.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollegeDao extends JpaRepository<College, Integer> {
    College findCollegeById(int id);

    College findCollegeByName(String name);

    List<College> findAll();
}
