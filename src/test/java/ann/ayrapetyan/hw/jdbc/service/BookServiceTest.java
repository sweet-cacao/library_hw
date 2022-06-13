package ann.ayrapetyan.hw.jdbc.service;

import ann.ayrapetyan.hw.jdbc.domain.Author;
import ann.ayrapetyan.hw.jdbc.domain.Book;
import ann.ayrapetyan.hw.jdbc.domain.BookComment;
import ann.ayrapetyan.hw.jdbc.domain.Genre;
import ann.ayrapetyan.hw.jdbc.jpa.AuthorRepository;
import ann.ayrapetyan.hw.jdbc.jpa.BookCommentRepository;
import ann.ayrapetyan.hw.jdbc.jpa.BookRepository;
import ann.ayrapetyan.hw.jdbc.jpa.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataMongoTest
@ContextConfiguration
class BookServiceTest {

    BookService service;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepositoryJpa;
    @Autowired
    GenreRepository genreRepositoryJpa;
    @Autowired
    BookCommentRepository bookComment;

    @BeforeEach
    public void setup(){
        Genre romantic = new Genre(1L, "Romantic");
        Genre detective = new Genre(2L, "Detective");
        Genre drama = new Genre(3L, "Drama");
        genreRepositoryJpa.save(romantic);
        genreRepositoryJpa.save(detective);
        genreRepositoryJpa.save(drama);
        Author agata = new Author(1L, "Agata", "Christy");
        Author charlotta = new Author(2L, "Charlotta", "Bronte");
        Author lev = new Author(3L, "Lev", "Tolstoy");
        authorRepositoryJpa.save(agata);
        authorRepositoryJpa.save(charlotta);
        authorRepositoryJpa.save(lev);
        BookComment comment1 = new BookComment(1L, "nice book");
        BookComment comment2 = new BookComment(2L, "good book");
        BookComment comment3 = new BookComment(3L, "best book");
        BookComment comment4 = new BookComment(4L, "bad");
        BookComment comment5 = new BookComment(5L, "i dont like");
        List<BookComment> comments1Book = new ArrayList<>();
        comments1Book.add(comment1);
        comments1Book.add(comment2);
        comments1Book.add(comment3);
        List<BookComment> comments2Book = new ArrayList<>();
        comments2Book.add(comment4);
        comments2Book.add(comment5);
        bookRepository.save(new Book(1L, "Jane Air", romantic, charlotta, comments1Book));
        bookRepository.save(new Book(2L, "Jane Airm", drama, charlotta, comments2Book));
    }
    @BeforeEach
    public void init() {
        service = new BookService(bookRepository, authorRepositoryJpa, genreRepositoryJpa);
    }

    @Test
    void get() {
        Book book = new Book(1L, "Jane Air", new Genre(1, "Romantic"), new Author(2, "Charlotta", "Bronte"), List.of(new BookComment(1, "nice book"), new BookComment(2, "good book"), new BookComment(3, "best book")));
        assertEquals(book, service.get(1L));
    }

    @Test
    void get_noSuchBook() {
        assertNull(service.get(3));
    }

    @Test
    void getByName() {
        Book book = new Book(1, "Jane Air", new Genre(1, "Romantic"), new Author(2, "Charlotta", "Bronte"), List.of(new BookComment(1, "nice book"), new BookComment(2, "good book"), new BookComment(3, "best book")));
        assertEquals(book, service.getByName("Jane Air"));
    }

    @Test
    void getByName_noSuchBook() {
        assertNull(service.getByName("Jane"));
    }

    @Test
    @Rollback
    void create() {
        Book book = new Book(4L, "Romeo and Juliet", new Genre(4, "Novel"), new Author(4, "William", "Shakespeare"), new ArrayList<>());
        Book foundBook = service.create(book.getName(), "Novel", "William", "Shakespeare", "");
        assertEquals(book.getName(), foundBook.getName());
        assertEquals(book.getAuthor().getName(), foundBook.getAuthor().getName());
        assertEquals(book.getAuthor().getSurname(), foundBook.getAuthor().getSurname());
        assertEquals(book.getGenre().getName(), foundBook.getGenre().getName());
        assertEquals(book.getComments(), foundBook.getComments());
    }

    @Test
    @Rollback
    void update() {
        Book book = new Book(1L, "Jane m", new Genre(1, "Romantic"), new Author(2, "Charlotta", "Bronte"), List.of(new BookComment(1, "nice book"), new BookComment(2, "good book"), new BookComment(3, "best book")));
        assertEquals(book, service.updateName("Jane Air", "Jane m"));
    }

    @Test
    @Rollback
    void delete() {
        service.delete("Jane Air");
        assertNull(service.getByName("Jane Air"));
    }
}