package com.example.vocaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.vocaapi.entity.VocaAndClass;

@Repository
public interface VocaAndClassRepository extends JpaRepository<VocaAndClass, Long> {

    // vid로 관계찾기
    @Query(value = "select * from VocaAndClass where vid = :vid", nativeQuery = true)
    VocaAndClass findRelationByVid(Long vid);

    // wid로 관계찾기
    @Query(value = "select * from VocaAndClass where wid = :wid", nativeQuery = true)
    VocaAndClass findRelationByWid(Long wid);

    // 관계 추가

    // 관계 삭제
}
