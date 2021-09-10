package com.bit2bit.foro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit2bit.foro.model.Author;

public interface AuthorRepo extends JpaRepository<Author, Long> {
	Author findByUsernameAndPassword(String user, String pwd);
	Boolean existsByUsername(String user);
}
