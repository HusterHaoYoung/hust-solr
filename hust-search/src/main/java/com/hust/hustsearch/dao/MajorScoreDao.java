package com.hust.hustsearch.dao;

import com.hust.hustsearch.entity.MajorScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MajorScoreDao extends JpaRepository<MajorScore, Integer> {
    @Query(value = "select * from major_score  where  province_id = :provinceId AND year = :year AND college_id = :collegeId AND branch_id = :branchId", nativeQuery = true)
    List<MajorScore> findScore(@Param("provinceId") int provinceId, @Param("year") int year,@Param("collegeId") int collegeId,@Param("branchId") int branchId);
}
