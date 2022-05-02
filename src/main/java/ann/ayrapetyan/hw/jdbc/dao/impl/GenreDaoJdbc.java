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
public class GenreDaoJdbc {
    private final NamedParameterJdbcOperations op;

    public GenreDaoJdbc(NamedParameterJdbcOperations op) {
        this.op = op;
    }

    public void insert(String genre) {
        op.update("insert into genres (name) values (:name)",
                Map.of("name", genre));
    }

    public Genre getByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        return op.queryForObject(
                "select id, name from genres where name = :name", params, new GenreMapper()
        );
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
