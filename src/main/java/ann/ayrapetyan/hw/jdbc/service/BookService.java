package ann.ayrapetyan.hw.jdbc.service;

import ann.ayrapetyan.hw.jdbc.domain.Author;
import ann.ayrapetyan.hw.jdbc.domain.Book;
import ann.ayrapetyan.hw.jdbc.domain.Genre;
import ann.ayrapetyan.hw.jdbc.jpa.impl.AuthorRepositoryJpa;
import ann.ayrapetyan.hw.jdbc.jpa.impl.BookCommentRepositoryJpa;
import ann.ayrapetyan.hw.jdbc.jpa.impl.BookRepositoryJpa;
import ann.ayrapetyan.hw.jdbc.jpa.impl.GenreRepositoryJpa;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.transaction.Transactional;
import java.util.List;


@AllArgsConstructor
@ShellComponent
@Transactional
public class BookService {
    private final BookRepositoryJpa bookDaoJdbc;
    private final AuthorRepositoryJpa authorDaoJdbc;
    private final GenreRepositoryJpa genreDaoJdbc;
    private final BookCommentRepositoryJpa bookCommentRepositoryJpa;

    @ShellMethod("get")
    public Book get(@ShellOption final long id) {
        Book book = bookDaoJdbc.findById(id).orElse(null);
        if (book == null) {
            System.out.println("No such book.");
            return null;
        }
        System.out.println(book);
        return book;
    }

    @ShellMethod("get-by-name")
    public Book getByName(@ShellOption final String name) {
        Book book = bookDaoJdbc.findByName(name);
        if (book == null) {
            System.out.println("No such book.");
            return null;
        }
        System.out.println(book);
        return book;
    }

    @ShellMethod("create")
    public Book create(@ShellOption String bookName, @ShellOption String genre, @ShellOption String name, @ShellOption String surname) {
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
//    update-name "Jane Air" m

    @ShellMethod("update")
    public Book updateName(@ShellOption String bookName, @ShellOption String newBookName) {
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

    @ShellMethod("getAll")
    public void getAll(){
        List<Book> books =  bookDaoJdbc.findAll();
        books.forEach(System.out::println);
    }

    @ShellMethod("delete")
    public String delete(@ShellOption String bookName) {
        Book book = bookDaoJdbc.findByName(bookName);
        if (book == null) {
            System.out.println("No such book.");
            return "No such book.";
        }
        bookDaoJdbc.deleteById(book.getId());
        System.out.println("Book deleted");
        return "Book deleted";
    }

    private Author getAuthor(@ShellOption String name, @ShellOption String surname) {
        Author a = authorDaoJdbc.findByName(name, surname);
        if (a == null) {
            a = new Author();
            a.setName(name);
            a.setSurname(surname);
            a = authorDaoJdbc.save(a);
        }
        return a;
    }

    private Genre getGenre(@ShellOption String genre) {
        Genre g = genreDaoJdbc.findByName(genre);
        if (g == null) {
            g = new Genre();
            g.setName(genre);
            g = genreDaoJdbc.save(g);
        }
        return g;
    }

}
