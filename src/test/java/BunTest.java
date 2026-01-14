import org.junit.jupiter.api.Test;
import praktikum.Bun;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;



class BunTest {

    @Test
    void testGettingNameAndPrice() {
        Bun bun = new Bun("Вкусно (нет) и точка!", 100.5F);

        assertAll("Проверка цены и имени для Bun",
                () -> assertEquals("Вкусно (нет) и точка!", bun.getName(), "Неверное имя булки"),
                () -> assertEquals(100.5F, bun.getPrice(), "Неверная цена булки")
        );
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