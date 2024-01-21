package com.example.vocaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.vocaapi.entity.Folder;
import com.example.vocaapi.entity.Member;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {

    @Query(value = "select f from folder f join fetch member m on f.mid = m.mid", nativeQuery = true)
    List<Folder> findByMember(Long mid);
}
