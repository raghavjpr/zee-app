package com.zee.zee5app.dto;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// ORM mapping purpose
@Entity // entity class is used for ORM
// to customize the table
@Table(name = "register", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class Register {

	@Id // It will consider this column as Primary Key
	private String registerId;

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

	@NotNull
	private BigInteger contactNumber;

	@ManyToMany
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "registerId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
	private Set<Role> roles = new HashSet<Role>();

	@OneToOne(mappedBy = "register", cascade = CascadeType.ALL)
//	@JsonIgnore
//	@JsonSerialize(using = CustomListSerializer.class)
	private Login login;

}