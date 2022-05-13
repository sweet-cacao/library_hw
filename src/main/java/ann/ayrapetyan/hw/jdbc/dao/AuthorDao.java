package ann.ayrapetyan.hw.jdbc.dao;

import ann.ayrapetyan.hw.jdbc.domain.Author;

public interface AuthorDao {
    void insert(String name, String surname);

    Author getByNameAndSurname(String name, String surname);
}
