package ann.ayrapetyan.hw.jdbc.jpa;

import ann.ayrapetyan.hw.jdbc.domain.BookComment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookCommentRepository extends MongoRepository<BookComment, Long> {
}
