import org.junit.jupiter.api.Test;
import praktikum.Bun;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BunTest {

    @Test
    void testGettingNameAndPrice() {
        Bun bun = new Bun("Вкусно (нет) и точка!", 100.5F);
        assertEquals("Вкусно (нет) и точка!", bun.getName());
        assertEquals(100.5F, bun.getPrice());
    }

    @Test
    void testCheckingEmptyName() {
        Bun bun = new Bun("", 100.5F);
        assertEquals("", bun.getName());
    }

    @Test
    void testZeroPrice() {
        Bun bun = new Bun("Вкусно (нет) и точка!", 0F);
        assertEquals(0F, bun.getPrice());
    }
}