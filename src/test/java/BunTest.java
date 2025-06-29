import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.Bun;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {



        @Test
        @DisplayName("Конструктор и геттеры")
        void testBunConstructorAndGetters() {
            Bun bun = new Bun("Sesame", 50.5f);
            assertEquals("Sesame", bun.getName());
            assertEquals(50.5f, bun.getPrice());

    }
}
