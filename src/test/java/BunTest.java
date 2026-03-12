
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import praktikum.Bun;
import praktikum.Database;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BunTest {


    private Database database = new Database();

    @ParameterizedTest(name = "Проверка булочки с именем {0}, цена {1}")
    @CsvSource({
            "black bun, 100",
            "white bun, 200",
            "red bun, 300"
    })
    void bunDetailsTest(String expectedName, double expectedPrice) {
        List<Bun> buns = database.availableBuns();

        boolean foundMatch = false;
        for (Bun bun : buns) {
            if (expectedName.equals(bun.getName())) {
                assertEquals(expectedPrice, bun.getPrice(), 0.01,
                        () -> "Цена булочки '" + expectedName + "' неверна");
                foundMatch = true;
                break;
            }
        }

        if (!foundMatch) {
            throw new AssertionError("Булочка с именем '" + expectedName + "' отсутствует");
        }
    }

    @Test
    public void getBunNameTest() {
        Bun bun = new Bun("Black Bun", 100.0f);
        String checkedBunName = bun.getName();
        assertEquals("Black Bun", checkedBunName);
    }

    @Test
    public void getBunPriceTest() {
        Bun bun = new Bun("White Bun", 150.0f);
        float checkedBunPrice = bun.getPrice();
        assertEquals(150.0f, checkedBunPrice,0.01);
      }
}


