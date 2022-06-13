package ann.ayrapetyan.hw.jdbc.jpa;

import ann.ayrapetyan.hw.jdbc.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
    Book findByName(String name);
    void deleteById(Long id);
}
