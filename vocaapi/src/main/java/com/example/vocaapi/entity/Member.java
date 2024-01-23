package com.example.vocaapi.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mid;

	@Column
	private String password;

	@Column
	private String nickname;

	@Column
	private String email;

	@Column
	@Enumerated(EnumType.STRING)
	private Authority authority;

	@CreationTimestamp
	private LocalDateTime createdAt = LocalDateTime.now();

	@UpdateTimestamp
	private LocalDateTime updatedAt = LocalDateTime.now();

	public String filename;

	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
	private List<Folder> folders;
}