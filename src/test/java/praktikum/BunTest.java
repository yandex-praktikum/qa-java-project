package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    @Test
    void getNameReturnsName() {
        Bun bun = new Bun("abrakadabra", 15);
        assertEquals("abrakadabra", bun.getName());
    }

    @Test
    void getPriceReturnsPrice() {
        Bun bun = new Bun("abrakadabra", 15);
        assertEquals(15, bun.getPrice());
    }

}
