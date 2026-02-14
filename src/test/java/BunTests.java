import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.Bun;

public class BunTests {

    Bun bun;
    final String BUN_NAME = "Bun Name";
    final float BUN_PRICE = 11;

    @BeforeEach
    @DisplayName("Создание объекта Bun с тестовыми параметрами")
    void setUp(){
        bun = new Bun(BUN_NAME, BUN_PRICE);
    }

    @Test
    @DisplayName("Метод getName() должен возвращать название булки")
    void getNameReturnsCurrentName(){
        String actual = bun.getName();
        Assertions.assertEquals(BUN_NAME, actual);
    }

    @Test
    @DisplayName("Метод getPrice() должен возвращать цену булки")
    void getPriceReturnCurrentPrice(){
        float actual = bun.getPrice();
        Assertions.assertEquals(BUN_PRICE, actual);
    }
}
