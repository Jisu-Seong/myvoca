package com.example.vocaapi.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.vocaapi.entity.Folder;
import com.example.vocaapi.entity.Vocabulary;

@Repository
public interface VocaRepository extends JpaRepository<Vocabulary, Long> {

    @Query(value = "select v from vocabulary v join fetch folder f on v.fid = f.fid")
    Page<Vocabulary> findVocaPageListByFolder(Folder folder, Pageable pageable);

    Optional<Vocabulary> findByVid(Long vid);
}
