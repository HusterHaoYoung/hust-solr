package com.hust.hustsearch.dao;

import com.hust.hustsearch.entity.College;
import com.hust.hustsearch.entity.Webnews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author :younghao
 * @ClassName: WebnewsDao
 * @Description: TODO
 * @date : 12/10/19  7:12 PM
 */
public interface WebnewsDao extends JpaRepository<Webnews, Integer> {
    @Query(value = "select * from webnews  where  wid = :wid limit :num",nativeQuery = true)
    List<Webnews> findByWid(@Param("wid") String wid, @Param("num") int limit);
}
