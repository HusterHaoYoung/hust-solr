package com.hust.hustsearch.dao;

import com.hust.hustsearch.entity.ResearchDirection;
import com.hust.hustsearch.entity.ResearchResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author :younghao
 * @ClassName: ResearchResultDao
 * @Description: TODO
 * @date : 12/4/19  9:37 PM
 */
public interface ResearchResultDao extends JpaRepository<ResearchDirection, Integer> {
    @Query(value = "select rr.id , rr.title,rr.link from research_result rr , lab_result lr where  lr.lab_id = :labId  AND  lr.rid = rr.id",nativeQuery = true)
    List<ResearchResult> findByLabId(@Param("labId") int labId);
}
