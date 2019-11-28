package com.hust.hustsearch.dao;

import com.hust.hustsearch.entity.Lab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabDao extends JpaRepository<Lab,Integer> {
    List<Lab> findAll();
}
