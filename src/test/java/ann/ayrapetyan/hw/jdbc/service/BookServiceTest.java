package ann.ayrapetyan.hw.jdbc.service;

import ann.ayrapetyan.hw.jdbc.dao.impl.AuthorDaoJdbc;
import ann.ayrapetyan.hw.jdbc.dao.impl.BookDaoJdbc;
import ann.ayrapetyan.hw.jdbc.dao.impl.GenreDaoJdbc;
import ann.ayrapetyan.hw.jdbc.domain.Author;
import ann.ayrapetyan.hw.jdbc.domain.Book;
import ann.ayrapetyan.hw.jdbc.domain.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@JdbcTest
@Import({AuthorDaoJdbc.class, BookDaoJdbc.class, GenreDaoJdbc.class})
class BookServiceTest {

    BookService service;
    @Autowired
    AuthorDaoJdbc authorDaoJdbc;
    @Autowired
    BookDaoJdbc bookDaoJdbc;
    @Autowired
    GenreDaoJdbc genreDaoJdbc;

    @BeforeEach
    public void init() {
        service = new BookService(bookDaoJdbc, authorDaoJdbc, genreDaoJdbc);
    }

    @Test
    void get() {
        Book book = new Book(1, "Jane Air", new Genre(1, "Romantic"), new Author(2, "Charlotta", "Bronte"));
        assertEquals(book, service.get(1));
    }

    @Test
    void get_noSuchBook() {
        assertNull(service.get(3));
    }

    @Test
    void getByName() {
        Book book = new Book(1, "Jane Air", new Genre(1, "Romantic"), new Author(2, "Charlotta", "Bronte"));
        assertEquals(book, service.getByName("Jane Air"));
    }

    @Test
    void getByName_noSuchBook() {
        assertNull(service.getByName("Jane"));
    }

    @Test
    @Rollback
    void create() {
        Book book = new Book(2, "Romeo and Juliet", new Genre(4, "Novel"), new Author(4, "William", "Shakespeare"));
        assertEquals(book, service.create(book.getName(), "Novel", "William", "Shakespeare"));
    }

    @Test
    @Rollback
    void update() {
        Book book = new Book(1, "Jane Air", new Genre(1, "Romantic"), new Author(3, "Lev", "Tolstoy"));
        assertEquals(book, service.update("Jane Air", "Romantic", "Lev", "Tolstoy"));
    }

    @Test
    @Rollback
    void delete() {
        service.delete("Jane Air");
        assertNull(service.getByName("Jane Air"));
    }
}