package com.example.vocaapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.vocaapi.entity.Tag;
import com.example.vocaapi.entity.Vocabulary;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    // 한 단어의 모든 태그 리스트(쿼리?)를 구하기 위한
    // 하나의 wordclass 찾기
    Optional<Tag> findByTid(Long tid);

    // 클래스 이름에 대하여 존재하는지 찾기
    @Query(value = "select * from tag where tagname = :tagname", nativeQuery = true)
    Tag findByTagname(String tagname);

    @Query(value = "SELECT tag.tagname " +
            "FROM tag " +
            "LEFT JOIN relation ON tag.tid = relation.tid " +
            "LEFT JOIN vocabulary ON relation.vid = vocabulary.vid " +
            "WHERE vocabulary.vid = :vid", nativeQuery = true)
    List<String> getTagList(Long vid);
}
