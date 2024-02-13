package com.example.vocaapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.vocaapi.entity.Folder;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {

    @Query(value = "select * from folder where folder.email = :email", nativeQuery = true)
    List<Folder> findByMember(String email);

    Optional<Folder> findByFid(Long fid);
}
