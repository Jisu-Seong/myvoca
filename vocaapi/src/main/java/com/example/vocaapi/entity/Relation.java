package com.example.vocaapi.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Relation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;

    @ManyToOne
    @JoinColumn(name = "vid")
    private Vocabulary vocabulary;

    @ManyToOne
    @JoinColumn(name = "tid")
    private Tag tag;

    public Relation(Vocabulary v, Tag t) {
        this.vocabulary = v;
        this.tag = t;
    }

    public void changeVoca(Vocabulary v) {
        this.vocabulary = v;
    }

    public void changeTag(Tag t) {
        this.tag = t;
    }
}
