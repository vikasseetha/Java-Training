package com.rgt.bookstore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rgt.bookstore.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
	List<Book> findByAuthor(String author);

	List<Book> findByActiveTrue();

	Page<Book> findAll(Pageable pages);
}
