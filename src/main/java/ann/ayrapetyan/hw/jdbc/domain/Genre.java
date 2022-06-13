package ann.ayrapetyan.hw.jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("genres")
public class Genre {
    @Id
    @Generated
    private long id;
    private String name;
}
