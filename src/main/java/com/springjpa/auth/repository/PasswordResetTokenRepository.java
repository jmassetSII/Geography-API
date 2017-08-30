package com.springjpa.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springjpa.auth.entity.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

}
