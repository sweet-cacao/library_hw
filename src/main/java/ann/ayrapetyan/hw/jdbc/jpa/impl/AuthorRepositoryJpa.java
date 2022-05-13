package ann.ayrapetyan.hw.jdbc.jpa.impl;

import ann.ayrapetyan.hw.jdbc.domain.Author;
import ann.ayrapetyan.hw.jdbc.jpa.AuthorRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class AuthorRepositoryJpa implements AuthorRepository {
    @PersistenceContext
    private final EntityManager em;

    public AuthorRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Author save(Author author) {
        if (author.getId() <= 0) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public Author findByName(String name, String surname) {
        try {
            TypedQuery<Author> query = em.createQuery("select s " +
                            "from Author s " +
                            "where s.name = :name and  s.surname = :surname",
                    Author.class);
            query.setParameter("name", name);
            query.setParameter("surname", surname);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }
}
