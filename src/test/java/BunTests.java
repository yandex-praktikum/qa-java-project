import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import praktikum.Bun;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTests {

    @ParameterizedTest
    @CsvSource({
            "Краторная булка N-200i, 1255",
            "Флюоресцентная булка R2-D3, 999"
    })
    public void bunConstructorTest(String bunName, int bunPrice) {
        Bun bun = new Bun(bunName, bunPrice);
        assertEquals(bunName, bun.getName());
        assertEquals(bunPrice, bun.getPrice());
    }
}