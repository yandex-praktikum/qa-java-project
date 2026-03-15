package praktikum;

import constants.TestMessages;
import data.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BunTest {
    private Bun bun;
    private String name;
    private float price;

    @BeforeEach
    void setUp() {
        TestData data = new TestData();
        name = data.getName();
        price = data.getPrice();
        bun = new Bun(name, price);
    }

    @Test
    void getNameReturnsName() {
        assertEquals(name, bun.getName(), TestMessages.ERROR);
    }

    @Test
    void getPriceReturnsPrice() {
        assertEquals(price, bun.getPrice(), TestMessages.ERROR);
    }
}