package com.example.vocaapi.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.vocaapi.entity.Relation;

@Repository
public interface RelationRepository extends JpaRepository<Relation, Long> {

    // vid로 관계찾기
    @Query(value = "select * from Relation where vid = :vid", nativeQuery = true)
    List<Relation> findRelationByVid(Long vid);

    // wid로 관계찾기
    @Query(value = "select * from Relation where tid = :tid", nativeQuery = true)
    List<Relation> findRelationByTid(Long tid);

    // 관계 추가

    // 관계 삭제
    @Query(value = "delete from Relation where vid = :vid", nativeQuery = true)
    void deleteRelationByVid(Long vid);
}
