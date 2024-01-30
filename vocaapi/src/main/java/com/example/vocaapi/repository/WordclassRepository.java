package com.example.vocaapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.vocaapi.entity.Wordclass;

@Repository
public interface WordclassRepository extends JpaRepository<Wordclass, Long> {

    // 한 단어의 모든 태그 리스트(쿼리?)를 구하기 위한
    // 하나의 wordclass 찾기
    Optional<Wordclass> findByWid(Long wid);

    // 클래스 이름에 대하여 존재하는지 찾기
    @Query(value = "select * from wordclass where classname = :classname", nativeQuery = true)
    Optional<Wordclass> findByClassname(String classname);

}
