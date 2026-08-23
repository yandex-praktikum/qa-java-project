import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Burger;
import praktikum.Bun;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;

@RunWith(Parameterized.class)
public class BurgerGetReceiptTest {

    // Булочка для теста
    private final Bun bun;

    // Список ингредиентов для теста
    private final List<Ingredient> ingredients;

    // Ожидаемый результат работы метода getReceipt()
    private final String expectedReceipt;

    // Конструктор
    public BurgerGetReceiptTest(Bun bun, List<Ingredient> ingredients, String expectedReceipt) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedReceipt = expectedReceipt;
    }

    // Набор параметров для параметризованных тестов
    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {
                        // Первый набор данных: булочка и два ингредиента
                        new Bun("black bun", 100f),
                        List.of(
                                new Ingredient(IngredientType.SAUCE, "sour cream", 50f),
                                new Ingredient(IngredientType.FILLING, "cutlet", 100f)

                        ),

                        // Ожидаемый чек для первого набора данных
                        "(==== black bun ====)" + System.lineSeparator() +
                                "= sauce sour cream =" + System.lineSeparator() +
                                "= filling cutlet =" + System.lineSeparator() +
                                "(==== black bun ====)" + System.lineSeparator() +
                                System.lineSeparator() +
                                "Price: 350,000000" + System.lineSeparator()
                },
                {
                        // Второй набор данных: булочка и три ингредиента
                        new Bun("white bun", 100f),
                        List.of(
                                new Ingredient(IngredientType.FILLING, "cutlet", 150f),
                                new Ingredient(IngredientType.SAUCE, "chili sauce", 50f),
                                new Ingredient(IngredientType.FILLING, "sausage", 50f)
                        ),

                        // Ожидаемый чек для второго набора данных
                        "(==== white bun ====)" + System.lineSeparator() +
                                "= filling cutlet =" + System.lineSeparator() +
                                "= sauce chili sauce =" + System.lineSeparator() +
                                "= filling sausage =" + System.lineSeparator() +
                                "(==== white bun ====)" + System.lineSeparator() +
                                System.lineSeparator() +
                                "Price: 450,000000" + System.lineSeparator()
                },
                {
                        // Третий набор данных: булочка и три ингредиента
                        new Bun("red bun", 100f),
                        List.of(
                                new Ingredient(IngredientType.SAUCE, "hot sauce", 50f),
                                new Ingredient(IngredientType.FILLING, "sausage", 50f),
                                new Ingredient(IngredientType.FILLING, "dinosaur", 200f)
                        ),

                        // Ожидаемый чек для третьего набора данных
                        "(==== red bun ====)" + System.lineSeparator() +
                                "= sauce hot sauce =" + System.lineSeparator() +
                                "= filling sausage =" + System.lineSeparator() +
                                "= filling dinosaur =" + System.lineSeparator() +
                                "(==== red bun ====)" + System.lineSeparator() +
                                System.lineSeparator() +
                                "Price: 500,000000"  + System.lineSeparator()
                },
        };
    }

    @Test
    public void testGetReceipt() {
        // Создаём новый объект Burger
        Burger burger = new Burger();

        // Устанавливаем булочку для бургера
        burger.setBuns(bun);

        // Добавляем все ингредиенты из текущего набора тестовых данных
        ingredients.forEach(burger::addIngredient);

        // Получаем чек с помощью метода getReceipt()
        String receipt = burger.getReceipt();

        // Проверяем, что полученный чек полностью совпадает с ожидаемым
        Assert.assertEquals(expectedReceipt, receipt);
    }
}