import praktikum.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

 class BunTest {
    static Bun bun;

    @BeforeAll
    static void setUpAll() {
        bun = new Bun("Bulochka", 22);
    }

    @Test
    void getNameReturnsName() {
        String actual = bun.getName();
        assertEquals("Bulochka", actual);
    }

    @Test
    void getPriceReturnPrice() {
        float actual = bun.getPrice();
        assertEquals(22, actual);
    }
}
