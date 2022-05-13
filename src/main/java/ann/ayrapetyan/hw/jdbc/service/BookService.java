package ann.ayrapetyan.hw.jdbc.service;

import ann.ayrapetyan.hw.jdbc.dao.AuthorDao;
import ann.ayrapetyan.hw.jdbc.dao.BookDao;
import ann.ayrapetyan.hw.jdbc.dao.GenreDao;
import ann.ayrapetyan.hw.jdbc.dao.impl.AuthorDaoJdbc;
import ann.ayrapetyan.hw.jdbc.dao.impl.BookDaoJdbc;
import ann.ayrapetyan.hw.jdbc.dao.impl.GenreDaoJdbc;
import ann.ayrapetyan.hw.jdbc.domain.Author;
import ann.ayrapetyan.hw.jdbc.domain.Book;
import ann.ayrapetyan.hw.jdbc.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;

@ShellComponent
//@Transactional
public class BookService {
    BookDao bookDaoJdbc;
    AuthorDao authorDaoJdbc;
    GenreDao genreDaoJdbc;

    @Autowired
    public BookService(BookDaoJdbc bookDaoJdbc, AuthorDaoJdbc authorDaoJdbc, GenreDaoJdbc genreDaoJdbc) {
        this.bookDaoJdbc = bookDaoJdbc;
        this.authorDaoJdbc = authorDaoJdbc;
        this.genreDaoJdbc = genreDaoJdbc;
    }

    @ShellMethod
    public Book get(@ShellOption final long id) {
        Book book = bookDaoJdbc.getById(id);
        if (book == null) {
            System.out.println("No such book.");
            return null;
        }
        System.out.println(book);
        return book;
    }

    @ShellMethod
    public Book getByName(@ShellOption final String name) {
        Book book = bookDaoJdbc.getByName(name);
        if (book == null) {
            System.out.println("No such book.");
            return null;
        }
        System.out.println(book);
        return book;
    }

    @ShellMethod
    public Book create(@ShellOption String bookName, @ShellOption String genre, @ShellOption String name, @ShellOption String surname) {
        Genre g = getGenre(genre);
        Author a = getAuthor(name, surname);
        Book book = bookDaoJdbc.getByName(bookName);
        if (book != null) {
            System.out.println("Book already exists.");
            return null;
        }
        bookDaoJdbc.insert(bookName, g.getId(), a.getId());
        book = bookDaoJdbc.getByName(bookName);
        System.out.println(book);
        return book;
    }

    @ShellMethod
    public Book update(@ShellOption String bookName, @ShellOption String genre, @ShellOption String name, @ShellOption String surname) {
        Book book = bookDaoJdbc.getByName(bookName);
        if (book == null) {
            System.out.println("No such book.");
            return null;
        } else {
            Genre g = getGenre(genre);
            Author a = getAuthor(name, surname);
            bookDaoJdbc.update(book.getId(), g.getId(), a.getId());
            book = bookDaoJdbc.getById(book.getId());
        }
        System.out.println("Book updated");
        System.out.println(book);
        return book;
    }

    @ShellMethod
    public String delete(@ShellOption String bookName) {
        Book book = bookDaoJdbc.getByName(bookName);
        if (book == null) {
            System.out.println("No such book.");
            return "No such book.";
        }
        bookDaoJdbc.deleteById(book.getId());
        System.out.println("Book deleted");
        return "Book deleted";
    }

    private Author getAuthor(@ShellOption String name, @ShellOption String surname) {
        Author a = authorDaoJdbc.getByNameAndSurname(name, surname);
        if (a == null) {
            authorDaoJdbc.insert(name, surname);
            a = authorDaoJdbc.getByNameAndSurname(name, surname);
        }
        return a;
    }

    private Genre getGenre(@ShellOption String genre) {
        Genre g = genreDaoJdbc.getByName(genre);
        if (g == null) {
            genreDaoJdbc.insert(genre);
            g = genreDaoJdbc.getByName(genre);
        }
        return g;
    }

}
