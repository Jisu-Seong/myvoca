package com.example.vocaapi.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tid;

    private String tagname;

    @OneToMany(mappedBy = "tag")
    @ToString.Exclude
    private List<Relation> relations = new ArrayList<>();

    public Tag(String tagname) {
        this.tagname = tagname;
    }
}
