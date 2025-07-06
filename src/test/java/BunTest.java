import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] bunsData() {
        return new Object[][]{
            {"Булочка побогаче", 222.0F},
            {"Булочка попроще", 111.0F},
        };
    }

    @Test
    public void getPriceTest() {
        // Создаем объект булочки
        Bun bun = new Bun(name, price);
        // Проверяем, что результат совпаадет с переданным
        // Здесь для чисел с плавающей точкой добавляем дельту, чтобы сравнивалось корректно
        assertEquals(price, bun.getPrice(), 0.0F);
    }

    @Test
    public void getNameTest() {
        // Создаем объект булочки
        Bun bun = new Bun(name, price);
        // Проверяем, что результат совпадет с переданным
        assertEquals(name, bun.getName());
    }
}
