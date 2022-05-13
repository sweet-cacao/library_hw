package ann.ayrapetyan.hw.jdbc.jpa;

import ann.ayrapetyan.hw.jdbc.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Author save(Author author);

    Optional<Author> findById(long id);

    Author findByName(String name, String surname);
}
