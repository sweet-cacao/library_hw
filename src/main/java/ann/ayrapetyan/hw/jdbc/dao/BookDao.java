package ann.ayrapetyan.hw.jdbc.dao;

import ann.ayrapetyan.hw.jdbc.domain.Book;

public interface BookDao {
    Book getById(long id);

    Book getByName(String name);

    void insert(String name, Long genre, Long author);

    void deleteById(long id);

    void update(Long id, Long genre, Long author);
}
