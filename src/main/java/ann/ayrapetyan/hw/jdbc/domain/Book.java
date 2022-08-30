package ann.ayrapetyan.hw.jdbc.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("books")
@Getter
@Setter
public class Book {
    @Id
    @Generated
    private long id;
    private String name;
    @DBRef
    private Genre genre;
    @DBRef
    private Author author;
    public String getCommentsAsString() {
        StringBuilder commentString = new StringBuilder();
        for (BookComment c: comments) {
            commentString.append(c.getComment()).append(", ");
        }
        return commentString.toString();
    }
    @DBRef
    private List<BookComment> comments = new ArrayList<>();
}
