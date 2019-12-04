package com.hust.hustsearch.dao;

import com.hust.hustsearch.entity.ResearchDirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author :younghao
 * @ClassName: ResearchDirectionDao
 * @Description: TODO
 * @date : 12/2/19  5:12 PM
 */
public interface ResearchDirectionDao extends JpaRepository<ResearchDirection, Integer> {
    @Query(value = "select rs.id , rs.name from research_direction rs , teacher_direction td  where  td.tid = :tid  AND  td.did = rs.id",nativeQuery = true)
    List<ResearchDirection> findByTeacherId(@Param("tid") int teacherId);
    @Query(value = "select rs.id , rs.name from research_direction rs , lab_direction ld  where  ld.lab_id = :labId  AND  ld.did = rs.id",nativeQuery = true)
    List<ResearchDirection> findByLabId(@Param("labId") int labId);
}
