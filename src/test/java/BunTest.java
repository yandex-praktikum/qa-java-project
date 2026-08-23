import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BunTest {

    // Название булочки для текущего набора тестовых данных
    private final String name;

    // Цена булочки для текущего набора тестовых данных
    private final float price;

    // Допустимая погрешность при сравнении значений типа float
    private static final float DELTA = 0.01f;

    // Конструктор получает параметры для каждого запуска параметризованного теста
    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    // Набор тестовых данных с разными названиями и ценами булочек
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"black bun", 100f},
                {"white bun", 200f},
                {"red bun", 300f}
        });
    }

    @Test
    public void testGetName() {

        // Создаём объект Bun с текущими тестовыми данными
        Bun bun = new Bun(name, price);

        // Проверяем, что метод getName() возвращает ожидаемое название булочки
        Assert.assertEquals(name, bun.getName());
    }

    @Test
    public void testGetPrice() {

        // Создаём объект Bun с текущими тестовыми данными
        Bun bun = new Bun(name, price);

        // Проверяем, что метод getPrice() возвращает ожидаемую цену булочки
        Assert.assertEquals(price, bun.getPrice(), DELTA);
    }
}