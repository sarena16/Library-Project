package com.example.libraryproject.librarynt.infrastructure.repository;

import com.example.libraryproject.librarynt.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  // Optional<UserEntity> findByUsername(String username);
}
