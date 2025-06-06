import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test void appStarts() {
        assertDoesNotThrow(() ->
                ru.nsu.khubanov.App.main(new String[0])
        );
    }
}
