import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import praktikum.Bun;
import static org.junit.jupiter.api.Assertions.*;

public class BunTest {

    @Test
    public void testBunConstructorAndGetters() {
        String expectedName = "black bun";
        float expectedPrice = 100.0f;
        Bun bun = new Bun(expectedName, expectedPrice);
        assertEquals(expectedName, bun.getName());
        assertEquals(expectedPrice, bun.getPrice());
    }
    @ParameterizedTest
    @CsvSource({
            "white bun, 150.5",
            "red bun, 200.0",
            "special bun, 0.0",
            "test bun, 999.99"
    })
    public void testBunWithDifferentParameters(String name, float price) {
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName());
        assertEquals(price, bun.getPrice());
    }

    @Test
    public void testBunNameCanBeNull() {
        Bun bun = new Bun(null, 100.0f);
        assertNull(bun.getName());
        assertEquals(100.0f, bun.getPrice());
    }

    @ParameterizedTest
    @ValueSource(floats = {-100.0f, 0.0f, 100.0f, 999.99f, -0.5f})
    public void testBunWithDifferentPrices(float price) {
        Bun bun = new Bun("test bun", price);
        assertEquals(price, bun.getPrice());
    }
    @Test
    public void testBunPricePrecision() {
        Bun bun = new Bun("test bun", 100.123456f);
        assertEquals(100.123456f, bun.getPrice(), 0.000001f);
    }
    @Test
    public void testBunMultipleInstances() {
        Bun bun1 = new Bun("bun1", 100.0f);
        Bun bun2 = new Bun("bun2", 200.0f);
        assertNotSame(bun1, bun2);
        assertEquals("bun1", bun1.getName());
        assertEquals("bun2", bun2.getName());
        assertEquals(100.0f, bun1.getPrice());
        assertEquals(200.0f, bun2.getPrice());
    }

}