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
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fid;

    private String foldername;

    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    private LocalDateTime updatedAt = LocalDateTime.now();

    private boolean delFlag;

    // @ManyToOne
    // @JoinColumn(name = "mid")
    // private Member member;

    @OneToMany(mappedBy = "folder", cascade = CascadeType.REMOVE)
    private List<Vocabulary> vocabularies;
}
