package com.example.vocaapi.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vocabulary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vid;

    private String vocaname;

    @Column(columnDefinition = "boolean default false")
    private boolean isMarked;

    private String meaning;

    private String sentence;

    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    private LocalDateTime updatedAt = LocalDateTime.now();

    private boolean delFlag;

    @ManyToOne
    @JoinColumn(name = "fid")
    private Folder folder;

    public void changeVocaname(String vocaname) {
        this.vocaname = vocaname;
    }

    public void changeMark(boolean isMarked) {
        this.isMarked = isMarked;
    }

    public void changeMeanings(String meaning) {
        this.meaning = meaning;
    }

    public void changeSentence(String sentence) {
        this.sentence = sentence;
    }

    public void changeUpdateAt() {
        this.updatedAt = LocalDateTime.now();
    }

    @ManyToMany
    @JoinTable(name = "relation", joinColumns = @JoinColumn(name = "vid"), inverseJoinColumns = @JoinColumn(name = "tid"))
    private List<Tag> tags = new ArrayList<>();
}
