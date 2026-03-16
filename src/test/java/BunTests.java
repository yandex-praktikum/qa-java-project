import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

// Использование Parameterized runner из JUnit
@RunWith(Parameterized.class)
public class BunTests {

    private Bun bun;
    private String expectedName;
    private float expectedPrice;

    // Конструктор теста, принимающий параметры булочки и ожидаемых значений
    public BunTests(Bun bun, String expectedName, float expectedPrice) {
        this.bun = bun;
        this.expectedName = expectedName;
        this.expectedPrice = expectedPrice;
    }

    // Данные для тестирования
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new Bun("Black Bun", 100f), "Black Bun", 100f},
                {new Bun("White Bun", 200f), "White Bun", 200f},
                {new Bun("Red Bun", 300f), "Red Bun", 300f},
        });
    }

    // Основной тест, проверяющий правильность булочки
    @Test
    public void shouldCreateBunCorrectly() {
        assertEquals(expectedName, bun.getName());                      // Проверка названия
        assertEquals(expectedPrice, bun.getPrice(), 0.01f);       // Проверка цены с точностью до 0.01
    }
}