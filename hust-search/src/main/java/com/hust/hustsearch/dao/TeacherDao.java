package com.hust.hustsearch.dao;

import com.hust.hustsearch.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherDao extends JpaRepository<Teacher, Integer>, JpaSpecificationExecutor<Teacher> {
    Teacher findById(int id);

    List<Teacher> findByCollegeId(int collegeId);
    @Query(value = "select * from teacher  where  college_id = :college_id limit :num",nativeQuery = true)
    List<Teacher> findByCollegeId(@Param("college_id") int collegeId, @Param("num") int limit);

    List<Teacher> findByLabId(int LabId);
}
