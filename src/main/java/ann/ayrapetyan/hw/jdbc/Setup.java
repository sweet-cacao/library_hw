package ann.ayrapetyan.hw.jdbc;

import ann.ayrapetyan.hw.jdbc.domain.Author;
import ann.ayrapetyan.hw.jdbc.domain.Book;
import ann.ayrapetyan.hw.jdbc.domain.BookComment;
import ann.ayrapetyan.hw.jdbc.domain.Genre;
import ann.ayrapetyan.hw.jdbc.jpa.AuthorRepository;
import ann.ayrapetyan.hw.jdbc.jpa.BookCommentRepository;
import ann.ayrapetyan.hw.jdbc.jpa.BookRepository;
import ann.ayrapetyan.hw.jdbc.jpa.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Setup {
    BookRepository bookRepository;
    AuthorRepository authorRepositoryJpa;
    GenreRepository genreRepositoryJpa;
    BookCommentRepository bookComment;

    @Autowired
    public Setup(BookRepository bookRepository, AuthorRepository authorRepositoryJpa, GenreRepository genreRepositoryJpa, BookCommentRepository bookComment) {
        this.bookRepository = bookRepository;
        this.authorRepositoryJpa = authorRepositoryJpa;
        this.genreRepositoryJpa = genreRepositoryJpa;
        this.bookComment = bookComment;
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
}
