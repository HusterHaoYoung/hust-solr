package com.hust.hustsearch.dao;

import com.hust.hustsearch.entity.Lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LabDao extends JpaRepository<Lab,Integer> {
    List<Lab> findAll();
    @Query(value = "select * from lab  where  college_id = :college_id limit :num",nativeQuery = true)
    List<Lab> findByCollegeId(@Param("college_id") int collegeId, @Param("num") int limit);
}
