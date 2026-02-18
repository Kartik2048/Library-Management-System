package com.project.LibraryManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.LibraryManagementSystem.entity.Book;
import com.project.LibraryManagementSystem.entity.Borrowing;
import com.project.LibraryManagementSystem.entity.Member;
import com.project.LibraryManagementSystem.service.LibraryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired private LibraryService libraryService;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return libraryService.findAllBooks();
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        return ResponseEntity.ok(libraryService.addBook(book));
    }

    @PostMapping("/members")
    public ResponseEntity<Member> addMember(@Valid @RequestBody Member member) {
        return ResponseEntity.ok(libraryService.addMember(member));
    }

    @PostMapping("/borrow") // requestparam to be used as /borrow?bookId=1&memberId=1
    public ResponseEntity<Borrowing> borrowBook(@RequestParam(name="bookId", required = true) Long bookId, @RequestParam(name="memberId") Long memberId) {
        return ResponseEntity.ok(libraryService.borrowBook(bookId, memberId));
    }

    @PostMapping("/return") // requestparam to be used as /return?borrowingId=1
    public ResponseEntity<Book> returnBook(@RequestParam(name="borrowingId", required = true) Long borrowingId) {
        return ResponseEntity.ok(libraryService.returnBook(borrowingId));
    }
}