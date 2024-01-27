package com.example.vocaapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vocaapi.entity.Wordclass;

public interface WordclassRepository extends JpaRepository<Wordclass, Long> {

    // 한 단어의 모든 태그 리스트(쿼리?)를 구하기 위한
    // 하나의 wordclass 찾기
    Optional<Wordclass> findByWid(Long wid);

    // 클래스 추가(WordClass)

    // 클래스 삭제(wid)

}
