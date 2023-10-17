import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

public class MainTest {
    Dotenv dotenv;

    @BeforeAll
    public void createDotenv() {
        dotenv = Dotenv.configure()
                .directory(Path.of("", "src/test/resources").toString())
                .filename(".env")
                .load();
    }

    @Test
    public void mainTest() {

    }
}
