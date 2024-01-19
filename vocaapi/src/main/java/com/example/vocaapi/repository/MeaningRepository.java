package com.example.vocaapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.vocaapi.entity.Meaning;
import com.example.vocaapi.entity.Vocabulary;

@Repository
public interface MeaningRepository extends JpaRepository<Meaning, Long> {

    @Query(value = "select m from meaning join fetch vocabulary v on m.vid = v.vid")
    List<Meaning> findByVocabulary(Vocabulary vocabulary);

    Optional<Meaning> findByMeid(Long meid);

}
