package com.example.vocaapi.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.vocaapi.entity.Vocabulary;

@Repository
public interface VocaRepository extends JpaRepository<Vocabulary, Long> {

    @Query(value = "select * from vocabulary where v.fid = :fid", nativeQuery = true)
    Page<Vocabulary> findVocaPageListByFolder(Long fid, Pageable pageable);

    Optional<Vocabulary> findByVid(Long vid);
}
