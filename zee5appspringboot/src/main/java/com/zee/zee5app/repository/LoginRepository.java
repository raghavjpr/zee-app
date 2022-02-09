package com.zee.zee5app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {

	Boolean existsByUserName(String userName);

	@Modifying
	@Query("update Login l set l.password = ?2 where l.userName = ?1")
	int changePasswordByUserName(String userName, String password);

}
