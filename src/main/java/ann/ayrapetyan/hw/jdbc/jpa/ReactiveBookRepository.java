package ann.ayrapetyan.hw.jdbc.jpa;

import ann.ayrapetyan.hw.jdbc.domain.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReactiveBookRepository extends ReactiveMongoRepository<Book, Long> {
}
