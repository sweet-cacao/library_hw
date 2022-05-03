package ann.ayrapetyan.hw.jdbc.dao.impl;

import ann.ayrapetyan.hw.jdbc.domain.Author;
import ann.ayrapetyan.hw.jdbc.domain.Book;
import ann.ayrapetyan.hw.jdbc.domain.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@Repository
public class BookDaoJdbc implements ann.ayrapetyan.hw.jdbc.dao.BookDao {
    private final NamedParameterJdbcOperations op;

    public BookDaoJdbc(NamedParameterJdbcOperations op) {
        this.op = op;
    }

    @Override
    public Book getById(long id) {
        try {
            Map<String, Object> params = Collections.singletonMap("id", id);
            return op.queryForObject(
                    "select b.id, b.name, a.id author_id, a.name author_name, a.surname, g.id genre_id, g.name genre_name " +
                            "from books b left join authors a on " +
                            "b.author_id = a.id left join genres g on b.genre_id = g.id where b.id = :id", params, new BookMapper()
            );
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public Book getByName(String name) {
        try {
            Map<String, Object> params = Collections.singletonMap("name", name);
            return op.queryForObject(
                    "select b.id, b.name, a.id author_id, a.name author_name, a.surname, g.id genre_id, g.name genre_name " +
                            "from books b left join authors a on " +
                            "b.author_id = a.id left join genres g on b.genre_id = g.id where b.name = :name", params, new BookMapper()
            );
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public void insert(String name, Long genre, Long author) {
        op.update("insert into books (name, genre_id, author_id) values (:name, :genre, :author)",
                Map.of("name", name, "genre", genre, "author", author));
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        op.update(
                "delete from books where id = :id", params
        );
    }

    @Override
    public void update(Long id, Long genre, Long author) {
        op.update("update books set (genre_id, author_id) = (:genre, :author) where id = :id", Map.of("id", id, "genre", genre, "author", author));
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Genre genre = new Genre(resultSet.getLong("genre_id"), resultSet.getString("genre_name"));
            Author author = new Author(resultSet.getLong("author_id"), resultSet.getString("author_name"), resultSet.getString("surname"));
            return new Book(id, name, genre, author);
        }
    }
}
