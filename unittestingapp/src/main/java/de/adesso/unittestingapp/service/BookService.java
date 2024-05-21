package de.adesso.unittestingapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.adesso.unittestingapp.entity.Book;

@Service
public class BookService {

	@Autowired
	BookRepository bookRep;
	
	public void saveBook(Book book) {
		bookRep.save(book);
	}
	
	public void deleteBook(Book book) {
		bookRep.delete(book);
	}
	public Optional<Book>  findBookById(long id) {
		return bookRep.findById(id);
	}
	public Book findBookByTitle(String title) {
		return bookRep.findByTitle(title).get();
	}
	public Book findBookByTitleAuthorIsbn(Book book) {
		return bookRep.findByTitleAndAuthorAndIsbn(book.getTitle(), book.getAuthor(), book.getIsbn()).get();
	}
	public Optional<Book> findBookByIsbn(String isbn) {
		return bookRep.findByIsbn(isbn);
	}

	public boolean isAlreadyStored(String isbn) {
		Optional<Book> dbBook = findBookByIsbn(isbn);

		if(dbBook.isPresent()) return true;

		return false;
	}
}
