package com.example.vocaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.vocaapi.entity.Example;
import com.example.vocaapi.entity.Vocabulary;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {

    @Query(value = "select e from example e join fetch vocabulary v on e.vid = v.vid")
    List<Example> findByVocabulary(Vocabulary vocabulary);
}
