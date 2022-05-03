package ann.ayrapetyan.hw.jdbc.jpa;

import ann.ayrapetyan.hw.jdbc.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book student);

    Optional<Book> findById(long id);

    Book findByName(String name);

    List<Book> findAll();

    void deleteById(long id);
}
