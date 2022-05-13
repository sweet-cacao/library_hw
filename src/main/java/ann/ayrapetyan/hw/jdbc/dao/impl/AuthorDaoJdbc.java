package ann.ayrapetyan.hw.jdbc.dao.impl;

import ann.ayrapetyan.hw.jdbc.dao.AuthorDao;
import ann.ayrapetyan.hw.jdbc.domain.Author;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations op;

    public AuthorDaoJdbc(NamedParameterJdbcOperations op) {
        this.op = op;
    }

    @Override
    public void insert(String name, String surname) {
        op.update("insert into authors (name, surname) values (:name, :surname)",
                Map.of("name", name, "surname", surname));
    }

    @Override
    public Author getByNameAndSurname(String name, String surname) {
        try {
            Map<String, Object> params = Map.of("name", name, "surname", surname);
            return op.queryForObject(
                    "select id, name, surname from authors where name = :name and surname = :surname", params, new AuthorMapper()
            );
        } catch (Exception e) {
            return null;
        }

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
