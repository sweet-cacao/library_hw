package ann.ayrapetyan.hw.jdbc.jpa.impl;

import ann.ayrapetyan.hw.jdbc.domain.BookComment;
import ann.ayrapetyan.hw.jdbc.jpa.BookCommentRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class BookCommentRepositoryJpa implements BookCommentRepository {
    @PersistenceContext
    private final EntityManager em;

    public BookCommentRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public BookComment save(BookComment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public Optional<BookComment> findById(long id) {
        return Optional.ofNullable(em.find(BookComment.class, id));
    }

    @Override
    public void deleteById(long id) {
        em.remove(em.find(BookComment.class, id));
    }
}
