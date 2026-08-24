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

    private static final float BUN_PRICE = 100f;
    private static final float SAUCE_PRICE = 50f;
    private static final float CUTLET_PRICE = 100f;
    private static final float EXPENSIVE_CUTLET_PRICE = 150f;
    private static final float SAUSAGE_PRICE = 50f;
    private static final float DINOSAUR_PRICE = 200f;
    private static final String FIRST_BURGER_PRICE = "350,000000";
    private static final String SECOND_BURGER_PRICE = "450,000000";
    private static final String THIRD_BURGER_PRICE = "500,000000";

    // Набор параметров для параметризованных тестов
    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {
                        // Первый набор данных: булочка и два ингредиента
                        new Bun("black bun", BUN_PRICE),
                        List.of(
                                new Ingredient(
                                        IngredientType.SAUCE,
                                        "sour cream",
                                        SAUCE_PRICE
                                ),
                                new Ingredient(
                                        IngredientType.FILLING,
                                        "cutlet",
                                        CUTLET_PRICE
                                )
                        ),

                        // Ожидаемый чек для первого набора данных
                        "(==== black bun ====)" + System.lineSeparator() +
                                "= sauce sour cream =" + System.lineSeparator() +
                                "= filling cutlet =" + System.lineSeparator() +
                                "(==== black bun ====)" + System.lineSeparator() +
                                System.lineSeparator() +
                                "Price: " + FIRST_BURGER_PRICE + System.lineSeparator()
                },
                {
                        // Второй набор данных: булочка и три ингредиента
                        new Bun("white bun", BUN_PRICE),
                        List.of(
                                new Ingredient(
                                        IngredientType.FILLING,
                                        "cutlet",
                                        EXPENSIVE_CUTLET_PRICE
                                ),
                                new Ingredient(
                                        IngredientType.SAUCE,
                                        "chili sauce",
                                        SAUCE_PRICE
                                ),
                                new Ingredient(
                                        IngredientType.FILLING,
                                        "sausage",
                                        SAUSAGE_PRICE
                                )
                        ),

                        // Ожидаемый чек для второго набора данных
                        "(==== white bun ====)" + System.lineSeparator() +
                                "= filling cutlet =" + System.lineSeparator() +
                                "= sauce chili sauce =" + System.lineSeparator() +
                                "= filling sausage =" + System.lineSeparator() +
                                "(==== white bun ====)" + System.lineSeparator() +
                                System.lineSeparator() +
                                "Price: " + SECOND_BURGER_PRICE + System.lineSeparator()
                },
                {
                        // Третий набор данных: булочка и три ингредиента
                        new Bun("red bun", BUN_PRICE),
                        List.of(
                                new Ingredient(
                                        IngredientType.SAUCE,
                                        "hot sauce",
                                        SAUCE_PRICE
                                ),
                                new Ingredient(
                                        IngredientType.FILLING,
                                        "sausage",
                                        SAUSAGE_PRICE
                                ),
                                new Ingredient(
                                        IngredientType.FILLING,
                                        "dinosaur",
                                        DINOSAUR_PRICE
                                )
                        ),

                        // Ожидаемый чек для третьего набора данных
                        "(==== red bun ====)" + System.lineSeparator() +
                                "= sauce hot sauce =" + System.lineSeparator() +
                                "= filling sausage =" + System.lineSeparator() +
                                "= filling dinosaur =" + System.lineSeparator() +
                                "(==== red bun ====)" + System.lineSeparator() +
                                System.lineSeparator() +
                                "Price: " + THIRD_BURGER_PRICE + System.lineSeparator()
                }
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