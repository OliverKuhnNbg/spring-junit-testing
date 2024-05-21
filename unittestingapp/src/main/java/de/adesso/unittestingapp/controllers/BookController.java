package de.adesso.unittestingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.adesso.unittestingapp.dtos.ResponseDto;
import de.adesso.unittestingapp.entity.Book;
import de.adesso.unittestingapp.service.BookService;

@RestController
@RequestMapping(path="/api/book")
public class BookController {
	
	@Autowired
	BookService bookService = new BookService();

	@GetMapping("/add-book")
	public void addBook(@RequestParam(value="title")String title, @RequestParam(value="author")String author, @RequestParam(value="isbn")String isbn) {
		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(author);
		book.setIsbn(isbn);
		
		// TODO avoid entry if already exists
		bookService.saveBook(book);
	}
	
	@PostMapping("/add-book-post")
	public ResponseEntity<ResponseDto> addBookPost(@RequestBody Book book) {		

		ResponseDto responseObj = new ResponseDto();
		responseObj.setBook(book.getTitle() + " - " + book.getAuthor() + " - " + book.getIsbn());

		boolean httpStatus = false;
		
		// checking if book already exists in db
		if( !bookService.isAlreadyStored(book.getIsbn()) ) {
			bookService.saveBook(book);
			responseObj.setMsg("book added to db");
			
			httpStatus = !httpStatus;
			
		} else {
			responseObj.setMsg("book already exists in db");
		}
		
		// add http header
		HttpHeaders httpHeader = new HttpHeaders();
		Long uniqueId = bookService.findBookByIsbn(book.getIsbn()).get().getId();
		httpHeader.add("unique", uniqueId.toString());
		
		// return
		return new ResponseEntity<ResponseDto>(responseObj, httpHeader, httpStatus ? HttpStatus.CREATED : HttpStatus.CONFLICT);
	}
	
	
	@GetMapping("/delete-book-by-title")
	public void deleteBookByTitle(@RequestParam(value="title")String title) {
		Book book = new Book();
		book = bookService.findBookByTitle(title);
		
		bookService.deleteBook(book);
	}
}
