package com.rgt.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rgt.bookstore.entity.Book;
import com.rgt.bookstore.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	BookService bookService;

	@PostMapping
	public Book createBook(@RequestBody Book book) {
		return bookService.createBook(book);
	}

	@GetMapping
	public List<Book> getAllBook() {
		return bookService.getAllBook();
	}

	@GetMapping("/books")
	public Page<Book> getAllBookWithPagination(
			@RequestParam(value = "pageNumber", defaultValue = "10", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "1", required = false) Integer pageSize) {
		Page<Book> allBook = bookService.getAllBookWithPagination(pageNumber, pageSize);
		return allBook;

	}

	@GetMapping("/search")
	public List<Book> searchBooks(@RequestParam String author) {
		return bookService.searchBookByName(author);

	}

	@GetMapping("/{id}")
	public Book getBookById(@PathVariable("id") Integer bookId) {
		return bookService.getBookById(bookId);
	}

	@PutMapping("/{id}")
	public Book updateBookById(@PathVariable("id") Integer bookId, @RequestBody Book book) {
		return bookService.updateBook(book, bookId);
	}

	@DeleteMapping("/{id}")
	public void deleteBookById(@PathVariable("id") Integer bookId) {
		bookService.deleteBook(bookId);
	}

	@GetMapping("/active")
	public List<Book> getActiveBooks() {
		return bookService.findByActiveTrue();
	}
}
