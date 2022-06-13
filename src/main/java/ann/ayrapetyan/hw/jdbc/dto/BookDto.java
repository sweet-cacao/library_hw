package ann.ayrapetyan.hw.jdbc.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookDto {
    private Long id;
    private String name;
    private String authorName;
    private String authorSurname;
    private String genreName;
    private String comments;
}
