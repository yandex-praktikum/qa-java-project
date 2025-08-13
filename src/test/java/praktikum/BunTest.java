package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import praktikum.constants.TestData;
import static org.junit.jupiter.api.Assertions.*;

public class BunTest {

    private Bun bun;

    @BeforeEach
    public void setUp() {
        bun = new Bun(TestData.BUN_NAME, TestData.BUN_MAX_PRICE);
    }

    @Test
    public void testGetName() {
        assertEquals(TestData.BUN_NAME, bun.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(TestData.BUN_MAX_PRICE, bun.getPrice(), TestData.DELTA);
    }
    @Test
    public void testBunWithNullNameAndGetPrice() {
        String name = null; // имя булки не задано
        Bun bunWithNullName = new Bun(name, TestData.BUN_MAX_PRICE);
        assertNull(bunWithNullName.getName(), "Название булочки не задано");
        assertEquals(TestData.BUN_MAX_PRICE, bunWithNullName.getPrice(), TestData.DELTA);
    }

    @Test
    public void testBunWithValidNameAndNegativePrice() {
        String name = TestData.BUN_NAME;

        // при попытке создать Bun с отрицательной ценой выбрасывается исключение + дополнил Bun.java
        assertThrows(IllegalArgumentException.class, () -> {
            new Bun(name, -100.0f);
        });
    }
}