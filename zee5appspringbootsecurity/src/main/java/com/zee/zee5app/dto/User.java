package com.zee.zee5app.dto;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email") })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	@NotBlank
	@Size(max = 20)
	private String username;

	@Size(max = 50)
	@NotNull
	private String firstName;

	@Size(max = 50)
	@NotNull
	private String lastName;

	@Size(max = 50)
	@Email
	private String email;

	@Size(max = 100)
	@NotNull
	private String password;

	private BigInteger contactNumber;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
	private Set<Role> roles = new HashSet<Role>();

	public User(@NotBlank @Size(max = 20) String username, @Size(max = 50) @NotNull String firstName,
			@Size(max = 50) @NotNull String lastName, @Size(max = 50) @Email String email,
			@Size(max = 100) @NotNull String password) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

}