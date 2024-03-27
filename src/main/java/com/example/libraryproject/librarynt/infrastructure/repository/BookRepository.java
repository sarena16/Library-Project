package com.example.libraryproject.librarynt.infrastructure.repository;

import com.example.libraryproject.librarynt.infrastructure.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
