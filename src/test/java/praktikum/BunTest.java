package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    private Bun bun;

    @BeforeEach
    public void setUp() {
        bun = new Bun("Black Bun", 200.0f);
    }

    @Test
    public void getNameReturnsCorrectName() {
        assertEquals("Black Bun", bun.getName());
    }

}
