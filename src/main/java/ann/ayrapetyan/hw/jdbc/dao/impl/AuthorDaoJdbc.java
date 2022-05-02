package ann.ayrapetyan.hw.jdbc.dao.impl;

import ann.ayrapetyan.hw.jdbc.domain.Author;
import ann.ayrapetyan.hw.jdbc.domain.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@Repository
public class AuthorDaoJdbc {
    private final NamedParameterJdbcOperations op;

    public AuthorDaoJdbc(NamedParameterJdbcOperations op) {
        this.op = op;
    }

    public void insert(String name, String surname) {
        op.update("insert into authors (name, surname) values (:name, :surname)",
                Map.of("name", name, "surname", surname));
    }

    public Author getByNameAndSurname(String name, String surname) {
        Map<String, Object> params = Map.of("name", name, "surname", surname);
        return op.queryForObject(
                "select id, name, surname from authors where name = :name and surname = :surname", params, new AuthorMapper()
        );
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            return new Author(id, name, surname);
        }
    }
}
