package ann.ayrapetyan.hw.jdbc.jpa;

import ann.ayrapetyan.hw.jdbc.domain.BookComment;
import org.springframework.data.repository.CrudRepository;

public interface BookCommentRepository extends CrudRepository<BookComment, Long> {
}
