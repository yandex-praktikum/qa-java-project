import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import praktikum.Bun;

public class BunTest {
    public static String name = "С корицей";
    public static float price = 10;

    @Test
    public void getName() {
        Bun bun = new Bun(name, price);
        String actualResult = name;
        String expectedResult = bun.getName();
        Assertions.assertEquals(expectedResult, actualResult, "Модель булочки должна совпадать");
    }

    @Test
    public void getPrice() {
        Bun bun = new Bun(name, price);
        Float actualResult = price;
        Float expectedResult = bun.getPrice();
        Assertions.assertEquals(expectedResult, actualResult, "Цена булочки должна совпадать");
    }
}
