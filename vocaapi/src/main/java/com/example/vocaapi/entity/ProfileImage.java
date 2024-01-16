package com.example.vocaapi.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Embeddable
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileImage {
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "mid")
    private Member member;
}
