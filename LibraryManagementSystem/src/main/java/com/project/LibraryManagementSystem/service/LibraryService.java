package com.project.LibraryManagementSystem.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.LibraryManagementSystem.entity.Book;
import com.project.LibraryManagementSystem.entity.Member;
import com.project.LibraryManagementSystem.entity.Borrowing;
import com.project.LibraryManagementSystem.repository.BookRepository;
import com.project.LibraryManagementSystem.repository.BorrowingRepository;
import com.project.LibraryManagementSystem.repository.MemberRepository;

import jakarta.transaction.Transactional;

@Service
public class LibraryService {

    @Autowired private BookRepository bookRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private BorrowingRepository borrowingRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    @Transactional
    public Borrowing borrowBook(Long bookId, Long memberId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        if (!book.isAvailable()) {
            throw new RuntimeException("Book is already borrowed");
        }

        book.setAvailable(false);
        bookRepository.save(book);

        Borrowing borrowing = new Borrowing();
        borrowing.setBook(book);
        borrowing.setMember(member);
        borrowing.setBorrowDate(LocalDate.now());
        
        return borrowingRepository.save(borrowing);
    }

    @Transactional
    public Book returnBook(Long borrowingId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new RuntimeException("Borrowing record not found"));
        
        Book book = borrowing.getBook();
        book.setAvailable(true);
        bookRepository.save(book);
        
        borrowing.setReturnDate(LocalDate.now());
        borrowingRepository.save(borrowing);
        
        return book;
    }
}
