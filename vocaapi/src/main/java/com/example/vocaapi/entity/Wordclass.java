package com.example.vocaapi.entity;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wordclass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wid;

    private String classname;

    @OneToMany(mappedBy = "wordclass")
    @ToString.Exclude
    private Set<VocaAndClass> vocabularies = new HashSet<>();

    public Wordclass(String classname) {
        this.classname = classname;
    }
}
