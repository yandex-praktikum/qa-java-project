package praktikum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class BunTest {

    @Test
    void testCreateBunWithRealData() {
        Bun bun = new Bun("Краторная булка N-200i", 1255);

        assertEquals("Краторная булка N-200i", bun.getName());
        assertEquals(1255, bun.getPrice());
    }

    @Test
    void testCreateBunWithAnotherRealData() {
        Bun bun = new Bun("Флюоресцентная булка R2-D3", 988);

        assertEquals("Флюоресцентная булка R2-D3", bun.getName());
        assertEquals(988, bun.getPrice());
    }

    @Test
    void testBunWithZeroPrice() {
        Bun bun = new Bun("Булочка по акции", 0);

        assertEquals("Булочка по акции", bun.getName());
        assertEquals(0, bun.getPrice());
    }

    @ParameterizedTest
    @CsvSource({
            "'Краторная булка N-200i', 1255",
            "'Флюоресцентная булка R2-D3', 988",
            "'Булочка с корицей', 200",
            "'', 100"
    })
    void testDifferentBuns(String name, float price) {
        Bun bun = new Bun(name, price);

        assertEquals(name, bun.getName());
        assertEquals(price, bun.getPrice());
    }

    @Test
    void testDirectFieldAccess() {
        Bun bun = new Bun("Тест", 150);

        assertEquals("Тест", bun.name);
        assertEquals(150, bun.price);
    }
}
