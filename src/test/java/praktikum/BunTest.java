package praktikum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class BunTest {


    @ParameterizedTest
    @CsvSource({
            "Белый хлеб, 50.0",
            "Ржаной хлеб, 75.5",
            "Багет, 120.0",
            "Круассан, 89.99"
    })
    @DisplayName("Параметризованный тест различных булок")
    void testVariousBuns(String bunName, float bunPrice) {
        // Arrange & Act
        Bun bun = new Bun(bunName, bunPrice);

        // Assert
        assertEquals(bunName, bun.getName());
        assertEquals(bunPrice, bun.getPrice(), 0.001);
    }
}