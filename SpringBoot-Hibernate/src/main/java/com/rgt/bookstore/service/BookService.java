package com.rgt.bookstore.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.rgt.bookstore.entity.Book;

public interface BookService
{
   Book createBook(Book book);
   
   Book updateBook(Book book,Integer bookId);
   
   public void deleteBook(Integer bookId);
   
   List<Book>getAllBook();
   
   Book getBookById(Integer bookId);
   
    Page<Book> getAllBookWithPagination(Integer pageNumber, Integer pageSize);

    List<Book> searchBookByName(String author);
    
    List<Book>findByActiveTrue();
    
}
