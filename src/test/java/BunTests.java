import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import praktikum.Bun;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTests {
    private Bun bun;

    private static final float EXPECTED_PRICE = 10.5f;
    private static final String EXPECTED_NAME = "Флюоресцентная булка";

    @BeforeEach
    public void setUp() {
        bun = new Bun(EXPECTED_NAME, EXPECTED_PRICE);
    }

    @Test
    public void getNameTest() {
        String actualName = bun.getName();
        assertEquals(EXPECTED_NAME, actualName,
                String.format("Bun's name was expected to be: %s, but actually was: %s", EXPECTED_NAME, actualName));
    }

    @Test
    public void getPriceTest() {
        float actualPrice = bun.getPrice();
        assertEquals(EXPECTED_PRICE, actualPrice,
                String.format("Bun's price was expected to be: %f, but actually was: %f", EXPECTED_PRICE, actualPrice));
    }
}
