package de.adesso.unittestingapp.service;

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
	public Book findBookByTitle(String title) {
		return bookRep.findByTitle(title).get();
	}
	public Book findBookByTitleAuthorIsbn(Book book) {
		return bookRep.findByTitleAndAuthorAndIsbn(book.getTitle(), book.getAuthor(), book.getIsbn()).get();
	}
	public Book findBookByIsbn(String isbn) {
		return bookRep.findByIsbn(isbn).isPresent() ? bookRep.findByIsbn(isbn).get() : new Book();
	}
	
	public boolean isBookAlreadyInDb(Book book) {
		Book dbBook = findBookByIsbn(book.getIsbn());
		if(dbBook.getTitle() == null) {
			return false;
		}
		
		return true;
	}
}
