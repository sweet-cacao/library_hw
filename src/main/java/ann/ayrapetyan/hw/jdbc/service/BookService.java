package ann.ayrapetyan.hw.jdbc.service;

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
@Transactional
public class BookService {
    BookDaoJdbc bookDaoJdbc;
    AuthorDaoJdbc authorDaoJdbc;
    GenreDaoJdbc genreDaoJdbc;

    @Autowired
    public BookService(BookDaoJdbc bookDaoJdbc, AuthorDaoJdbc authorDaoJdbc, GenreDaoJdbc genreDaoJdbc) {
        this.bookDaoJdbc = bookDaoJdbc;
        this.authorDaoJdbc = authorDaoJdbc;
        this.genreDaoJdbc = genreDaoJdbc;
    }

    @ShellMethod("get")
    public void get(@ShellOption final long id) {
        System.out.println(bookDaoJdbc.getById(id));
    }

    @ShellMethod("get")
    public void get(@ShellOption final String name) {
        System.out.println(bookDaoJdbc.getByName(name));
    }

    @ShellMethod("create")
    public void create(@ShellOption String bookName, @ShellOption String genre, @ShellOption String name, @ShellOption String surname) {
        Genre g = getGenre(genre);
        Author a = getAuthor(name, surname);
        Book book = bookDaoJdbc.getByName(bookName);
        if (book == null) {
            bookDaoJdbc.insert(bookName, g.getId(), a.getId());
            book = bookDaoJdbc.getByName(bookName);
        }
        System.out.println(book);
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

    @ShellMethod("update")
    public void update(@ShellOption String bookName, @ShellOption String genre, @ShellOption String name, @ShellOption String surname) {
        Book book = bookDaoJdbc.getByName(bookName);
        if (book == null) {
            System.out.println("No such book.");
            return;
        } else {
            Genre g = getGenre(genre);
            Author a = getAuthor(name, surname);
            bookDaoJdbc.update(book.getId(), g.getId(), a.getId());
            book = bookDaoJdbc.getById(book.getId());
        }
        System.out.println("Book updated");
        System.out.println(book);
    }

    @ShellMethod("delete")
    public void delete(@ShellOption String bookName) {
        Book book = bookDaoJdbc.getByName(bookName);
        if (book == null) {
            System.out.println("No such book.");
            return;
        }
        bookDaoJdbc.deleteById(book.getId());
        System.out.println("Book deleted");
    }

}
