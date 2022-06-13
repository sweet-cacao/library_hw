package ann.ayrapetyan.hw.jdbc.jpa;

import ann.ayrapetyan.hw.jdbc.domain.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    public Genre findByName(String name);
}
