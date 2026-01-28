package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BunTest {
    private float price;
    private String name;
    private Bun bun;

    @BeforeEach
    public void setUp() {
        price = 34;
        name = "Хотдог метеоритный";
        bun = new Bun(name, price);
    }

    @Test
    public void getNameTest() {
        String actualName = bun.getName();
        assertEquals(name, actualName);
    }

    @Test
    public void getPriceTest() {
        float actualPrice = bun.getPrice();
        assertEquals(price, actualPrice, 0);
    }
}
