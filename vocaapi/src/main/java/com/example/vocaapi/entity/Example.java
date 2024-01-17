package com.example.vocaapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Example {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eid;

    private String sentence;

    @ManyToOne
    @JoinColumn(name = "vid")
    private Vocabulary vocabulary;
}
