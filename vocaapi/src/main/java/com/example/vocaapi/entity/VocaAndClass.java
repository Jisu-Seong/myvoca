package com.example.vocaapi.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VocaAndClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vcid;

    @ManyToOne
    @JoinColumn(name = "vid")
    private Vocabulary vocabulary;

    @ManyToOne
    @JoinColumn(name = "wid")
    private Wordclass wordclass;

    public VocaAndClass(Vocabulary v, Wordclass w) {
        this.vocabulary = v;
        this.wordclass = w;
    }

    public void changeVoca(Vocabulary v) {
        this.vocabulary = v;
    }

    public void changeWordClass(Wordclass w) {
        this.wordclass = w;
    }
}
