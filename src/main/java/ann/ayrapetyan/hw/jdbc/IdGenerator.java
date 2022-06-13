package ann.ayrapetyan.hw.jdbc;


public class IdGenerator {
    private static Long id = 10L;

    private IdGenerator() {
    }

    public static Long getNextId() {
        id = id + 1;
        return id;
    }
}
