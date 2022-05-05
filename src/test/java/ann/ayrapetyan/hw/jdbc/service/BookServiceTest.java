package ann.ayrapetyan.hw.jdbc.service;

import ann.ayrapetyan.hw.jdbc.domain.Author;
import ann.ayrapetyan.hw.jdbc.domain.Book;
import ann.ayrapetyan.hw.jdbc.domain.Genre;
import ann.ayrapetyan.hw.jdbc.jpa.impl.AuthorRepositoryJpa;
import ann.ayrapetyan.hw.jdbc.jpa.impl.BookCommentRepositoryJpa;
import ann.ayrapetyan.hw.jdbc.jpa.impl.BookRepositoryJpa;
import ann.ayrapetyan.hw.jdbc.jpa.impl.GenreRepositoryJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import({AuthorRepositoryJpa.class, BookRepositoryJpa.class, GenreRepositoryJpa.class, BookCommentRepositoryJpa.class})
class BookServiceTest {

    BookService service;

    @Autowired
    BookRepositoryJpa bookRepository;
    @Autowired
    AuthorRepositoryJpa authorRepositoryJpa;
    @Autowired
    GenreRepositoryJpa genreRepositoryJpa;
    @Autowired
    BookCommentRepositoryJpa bookComment;

    @BeforeEach
    public void init() {
        service = new BookService(bookRepository, authorRepositoryJpa, genreRepositoryJpa, bookComment);
    }

    @Test
    void get() {
        Book book = new Book(1, "Jane Air", new Genre(1, "Romantic"), new Author(2, "Charlotta", "Bronte"), new ArrayList<>());
        assertEquals(book, service.get(1));
    }

    @Test
    void get_noSuchBook() {
        assertNull(service.get(3));
    }

    @Test
    void getByName() {
        Book book = new Book(1, "Jane Air", new Genre(1, "Romantic"), new Author(2, "Charlotta", "Bronte"), new ArrayList<>());
        assertEquals(book, service.getByName("Jane Air"));
    }

    @Test
    void getByName_noSuchBook() {
        assertNull(service.getByName("Jane"));
    }

    @Test
    @Rollback
    void create() {
        Book book = new Book(2, "Romeo and Juliet", new Genre(4, "Novel"), new Author(4, "William", "Shakespeare"), new ArrayList<>());
        assertEquals(book, service.create(book.getName(), "Novel", "William", "Shakespeare"));
    }

    @Test
    @Rollback
    void update() {
        Book book = new Book(1, "Jane m", new Genre(1, "Romantic"), new Author(2, "Charlotta", "Bronte"), new ArrayList<>());
        assertEquals(book, service.updateName("Jane Air", "Jane m"));
    }

    @Test
    @Rollback
    void delete() {
        service.delete("Jane Air");
        assertNull(service.getByName("Jane Air"));
    }
}