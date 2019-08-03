package com.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectmanager.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
