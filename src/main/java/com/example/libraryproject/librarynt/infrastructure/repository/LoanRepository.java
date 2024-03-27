package com.example.libraryproject.librarynt.infrastructure.repository;

import com.example.libraryproject.librarynt.infrastructure.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
}