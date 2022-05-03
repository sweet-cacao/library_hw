package ann.ayrapetyan.hw.jdbc.jpa.impl;

import ann.ayrapetyan.hw.jdbc.domain.BookComment;
import ann.ayrapetyan.hw.jdbc.jpa.BookCommentRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    public void updateNameById(long id, String comment) {
        Query query = em.createQuery("update BookComment s " +
                "set s.comment = :comment " +
                "where s.id = :id");
        query.setParameter("comment", comment);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from BookComment s " +
                "where s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
