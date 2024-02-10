package com.example.vocaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vocaapi.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
