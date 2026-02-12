package com.project.LibraryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.LibraryManagementSystem.entity.Borrowing;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {}
