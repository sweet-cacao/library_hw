package ann.ayrapetyan.hw.jdbc.service;

import ann.ayrapetyan.hw.jdbc.domain.Author;
import ann.ayrapetyan.hw.jdbc.domain.Book;
import ann.ayrapetyan.hw.jdbc.domain.Genre;
import ann.ayrapetyan.hw.jdbc.jpa.AuthorRepository;
import ann.ayrapetyan.hw.jdbc.jpa.BookRepository;
import ann.ayrapetyan.hw.jdbc.jpa.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class BookService {
    private final BookRepository bookDaoJdbc;
    private final AuthorRepository authorDaoJdbc;
    private final GenreRepository genreDaoJdbc;

    public Book get(long id) {
        Book book = bookDaoJdbc.findById(id).orElse(null);
        if (book == null) {
            System.out.println("No such book.");
            return null;
        }
        return book;
    }

    public Book getByName(final String name) {
        Book book = bookDaoJdbc.findByName(name);
        if (book == null) {
            System.out.println("No such book.");
            return null;
        }
        return book;
    }

    public Book create(String bookName, String genre, String name, String surname) {
        Genre g = getGenre(genre);
        Author a = getAuthor(name, surname);
        Book book = bookDaoJdbc.findByName(bookName);
        if (book != null) {
            System.out.println("Book already exists.");
            return null;
        }
        book = new Book();
        book.setName(bookName);
        book.setAuthor(a);
        book.setGenre(g);
        book = bookDaoJdbc.save(book);
        System.out.println(book);
        return book;
    }

    public Book updateName(String bookName, String newBookName) {
        Book book = bookDaoJdbc.findByName(bookName);
        if (book == null) {
            System.out.println("No such book.");
            return null;
        } else {
            book.setName(newBookName);
            bookDaoJdbc.save(book);
            book = bookDaoJdbc.findById(book.getId()).orElse(null);
        }
        System.out.println("Book updated");
        System.out.println(book);
        return book;
    }

    public void getAll() {
        bookDaoJdbc.findAll().forEach(System.out::println);
    }

    public void getAllComments(String bookName) {
        bookDaoJdbc.findByName(bookName).getComments().forEach(System.out::println);
    }

    public String delete(String bookName) {
        Book book = bookDaoJdbc.findByName(bookName);
        if (book == null) {
            System.out.println("No such book.");
            return "No such book.";
        }
        bookDaoJdbc.deleteById(book.getId());
        System.out.println("Book deleted");
        return "Book deleted";
    }

    private Author getAuthor(String name, String surname) {
        Author a = authorDaoJdbc.findByNameAndSurname(name, surname);
        if (a == null) {
            a = new Author();
            a.setName(name);
            a.setSurname(surname);
            a = authorDaoJdbc.save(a);
        }
        return a;
    }

    private Genre getGenre(String genre) {
        Genre g = genreDaoJdbc.findByName(genre);
        if (g == null) {
            g = new Genre();
            g.setName(genre);
            g = genreDaoJdbc.save(g);
        }
        return g;
    }

}
