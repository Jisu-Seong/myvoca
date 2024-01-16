package com.example.vocaapi.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "profileImg")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;

    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private String nickname;

    @NonNull
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    @NonNull
    @UpdateTimestamp
    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "profileImg")
    private List<ProfileImage> profileImgsProfileImages;

    @OneToMany(mappedBy = "folder")
    private List<Folder> folders;
}
