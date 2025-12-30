import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.Bun;

import static constants.bun.BunInt.PRICE_100;
import static constants.bun.BunString.GREEN_BUN;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTests {

    private Bun bun;

    @BeforeEach
    @DisplayName("Создание конструктора")
    void constrictorCreation() {
        bun = new Bun(GREEN_BUN, PRICE_100);
    }

    @Test
    @DisplayName("Проверка возврата значений функции получения названия булочки")
    void checkGetNameOfBun() {
        assertEquals(GREEN_BUN, bun.getName());
    }

    @Test
    @DisplayName("Проверка возврата значений функции получения цены булочки")
    void checkGetPriceOfBun() {
        assertEquals(PRICE_100, bun.getPrice());
    }
}
