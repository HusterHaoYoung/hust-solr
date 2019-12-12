package com.hust.hustsearch.dao;

import com.hust.hustsearch.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvinceDao extends JpaRepository<Province,Integer> {
    List<Province> findAll();
}
