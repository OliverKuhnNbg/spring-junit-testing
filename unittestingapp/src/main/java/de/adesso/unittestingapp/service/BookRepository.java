package de.adesso.unittestingapp.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.adesso.unittestingapp.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	Optional<Book> findByTitle(String title);
	Optional<Book> findByIsbn(String isbn);
	Optional<Book> findByTitleAndAuthorAndIsbn(String title, String author, String isbn);
	Long deleteByTitle(String title);
}
