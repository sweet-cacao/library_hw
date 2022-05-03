package ann.ayrapetyan.hw.jdbc.dao;

import ann.ayrapetyan.hw.jdbc.domain.Genre;

public interface GenreDao {
    void insert(String genre);

    Genre getByName(String name);
}
