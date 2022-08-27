package ann.ayrapetyan.hw.jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("books")
public class Book {
    @Id
    private long id;
    private String name;
    @DBRef
    private Genre genre;
    @DBRef
    private Author author;
    @DBRef
    private List<BookComment> comments = new ArrayList<>();
}
