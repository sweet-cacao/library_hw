package ann.ayrapetyan.hw.jdbc.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("authors")
public class Author {
    @Id
    @Generated
    private long id;
    private String name;
    private String surname;
}
