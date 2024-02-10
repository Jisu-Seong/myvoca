package com.example.vocaapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.vocaapi.entity.Folder;
import com.example.vocaapi.entity.Vocabulary;

@Repository
@EnableJpaRepositories
public interface VocaRepository extends JpaRepository<Vocabulary, Long> {

    // 폴더당 단어 리스트 조회
    // @Query(value = "select * from vocabulary where fid = :fid", nativeQuery =
    // true)
    // List<Vocabulary> findVocaPageListByFid(Long fid);

    // 특정 단어 코드로 단어 정보 조회
    // 한 태그에 대한 단어 리스트 조회를 위함
    Optional<Vocabulary> findByVid(Long vid);

    Page<Vocabulary> findByMarked(boolean isMarked, Pageable pageable);

    // 단어 추가

    // 단어 수정

    // 단어 삭제
}
