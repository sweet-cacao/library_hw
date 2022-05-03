package ann.ayrapetyan.hw.jdbc.jpa;

import ann.ayrapetyan.hw.jdbc.domain.BookComment;

import java.util.Optional;

public interface BookCommentRepository {
    BookComment save(BookComment comment);

    Optional<BookComment> findById(long id);

    void updateNameById(long id, String comment);

    void deleteById(long id);
}
