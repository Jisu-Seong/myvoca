package com.example.vocaapi.entity;

import java.time.LocalDateTime;
import java.util.List;

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

    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    private LocalDateTime updatedAt = LocalDateTime.now();

    private boolean delFlag;

    @ManyToOne
    @JoinColumn(name = "fid")
    private Folder folder;

    @OneToMany(mappedBy = "vocabulary", cascade = CascadeType.REMOVE)
    private List<Meaning> meanings;

    @OneToMany(mappedBy = "vocabulary", cascade = CascadeType.REMOVE)
    private List<Example> examples;

}
