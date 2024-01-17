package com.example.vocaapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meaning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meid;

    private String meanname;

    @ManyToOne
    @JoinColumn(name = "vid")
    private Vocabulary vocabulary;
}
