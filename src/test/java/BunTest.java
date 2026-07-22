import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;
    private static final float DELTA = 0.01f;

    // Конструктор для инициализации параметров
    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    // Метод, который предоставляет набор параметров для теста
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"black bun", 100f},
                {"white bun", 200f},
                {"red bun", 300f}
        });
    }

    // Тест для метода getName()
    @Test
    public void testGetName() {
        // Создаем тестовый объект
        Bun bun = new Bun(name, price);
        // Проверяем, что метод getName() возвращает правильное имя
        Assert.assertEquals(name, bun.getName());
    }

    // Тест для метода getPrice()
    @Test
    public void testGetPrice() {
        // Создаем тестовый объект
        Bun bun = new Bun(name, price);
        // Проверяем, что метод getPrice() возвращает правильную цену
        Assert.assertEquals(price, bun.getPrice(), DELTA);
    }
}