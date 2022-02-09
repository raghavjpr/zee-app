package com.zee.zee5app.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Register;

@Repository
public interface UserRepository extends JpaRepository<Register, String> {

	// custom JPA method // we will not write any definition // just only
	// declaration

	Boolean existsByEmail(String email);

	Boolean existsByContactNumber(BigInteger contactNumber);

	Boolean existsByEmailAndContactNumber(String email, BigInteger contactNumber);

}
