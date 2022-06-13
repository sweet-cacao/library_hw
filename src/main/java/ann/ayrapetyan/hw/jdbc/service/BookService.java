package ann.ayrapetyan.hw.jdbc.service;

import ann.ayrapetyan.hw.jdbc.IdGenerator;
import ann.ayrapetyan.hw.jdbc.domain.Author;
import ann.ayrapetyan.hw.jdbc.domain.Book;
import ann.ayrapetyan.hw.jdbc.domain.BookComment;
import ann.ayrapetyan.hw.jdbc.domain.Genre;
import ann.ayrapetyan.hw.jdbc.dto.BookDto;
import ann.ayrapetyan.hw.jdbc.jpa.AuthorRepository;
import ann.ayrapetyan.hw.jdbc.jpa.BookRepository;
import ann.ayrapetyan.hw.jdbc.jpa.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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

    public Book create(String bookName, String genre, String name, String surname, String comments) {
        Genre g = getGenre(genre);
        Author a = getAuthor(name, surname);
        Book book = bookDaoJdbc.findByName(bookName);
        if (book != null) {
            System.out.println("Book already exists.");
            return null;
        }
        book = new Book();
        book.setId(IdGenerator.getNextId());
        book.setName(bookName);
        book.setAuthor(a);
        book.setGenre(g);
        List<BookComment> newCommentList = new ArrayList<>();
        Arrays.stream(comments.split(", ")).forEach(it -> {
            if (! it.isEmpty()) {
                newCommentList.add(new BookComment(IdGenerator.getNextId(), it));
            }
        });
        book.setComments(newCommentList);
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

    public Book updateBook(Long id, BookDto dto) {
        Book book = bookDaoJdbc.findById(id).orElse(null);
        if (book == null) {
            System.out.println("No such book.");
            return null;
        } else {
            book.setName(dto.getName());
            Genre g = getGenre(dto.getGenreName());
            Author a = getAuthor(dto.getAuthorName(), dto.getAuthorSurname());
            book.setGenre(g);
            book.setAuthor(a);
            List<BookComment> newCommentList = new ArrayList<>();
            Arrays.stream(dto.getComments().split(", ")).forEach(it -> {
                if (! it.isEmpty()) {
                    newCommentList.add(new BookComment(IdGenerator.getNextId(), it));
                }
            });
            book.setComments(newCommentList);
            bookDaoJdbc.save(book);
            book = bookDaoJdbc.findById(book.getId()).orElse(null);
        }
        System.out.println("Book updated");
        System.out.println(book);
        return book;
    }

    public List<Book> getAll() {
        List<Book> books = bookDaoJdbc.findAll();
        books.forEach(System.out::println);
        return books;
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
   public String delete(long id) {
        Book book = bookDaoJdbc.findById(id).orElse(null);
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
            a.setId(IdGenerator.getNextId());
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
            g.setId(IdGenerator.getNextId());
            g.setName(genre);
            g = genreDaoJdbc.save(g);
        }
        return g;
    }

}
