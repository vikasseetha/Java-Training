package com.rgt.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rgt.bookstore.entity.Book;
import com.rgt.bookstore.repository.BookRepository;

@Service
public class BookImpl implements BookService {
	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book createBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Book updateBook(Book book, Integer bookId) {
		Book updateBook = bookRepository.findById(bookId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + bookId));
		updateBook.setTitle(book.getTitle());
		updateBook.setAuthor(book.getAuthor());
		updateBook.setPrice(book.getPrice());
		return bookRepository.save(updateBook);
	}

	@Override
	public void deleteBook(Integer bookId) {
		bookRepository.deleteById(bookId);
	}

	@Override
	public List<Book> getAllBook() {
		return (List<Book>) bookRepository.findAll();
	}

	@Override
	public Book getBookById(Integer bookId) {
		return bookRepository.findById(bookId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + bookId));
	}

	@Override
	public Page<Book> getAllBookWithPagination(Integer pageNumber, Integer pageSize) {
		Pageable pages = PageRequest.of(--pageNumber, pageSize);
		Page<Book> pageBook = bookRepository.findAll(pages);
		return pageBook;
	}

	@Override
	public List<Book> searchBookByName(String author) {
		return bookRepository.findByAuthor(author);
	}

	@Override
	public List<Book> findByActiveTrue() {
		return bookRepository.findByActiveTrue();
	}

}
