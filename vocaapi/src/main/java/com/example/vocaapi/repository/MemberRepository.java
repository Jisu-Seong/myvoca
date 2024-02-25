package com.example.vocaapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.vocaapi.entity.Member;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {
    @EntityGraph(attributePaths = { "memberRoleList" })
    @Query("select m from Member m where m.email = :email")
    Member getWithRoles(@Param("email") String email);

    Optional<Member> findByEmail(String email);

    @Query(value = "select count(*) from Member where member.email = :email", nativeQuery = true)
    int isExistEmail(String email);

}
