package com.project.LibraryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.LibraryManagementSystem.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}
