package ann.ayrapetyan.hw.jdbc.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Book {
    private final long id;
    private final String name;
    private final Genre genre;
    private final Author author;
}
