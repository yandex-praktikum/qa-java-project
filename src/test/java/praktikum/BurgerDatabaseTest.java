package praktikum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Интеграционные тесты Burger с Database")
public class BurgerDatabaseTest {

    @Test
    @DisplayName("Интеграционный тест: создание бургера из данных Database")
    public void testBurgerWithRealDatabase() {
        Database database = new Database();
        Burger burger = new Burger();

        // Берём реальные данные из БД
        Bun bun = database.availableBuns().get(0);
        Ingredient sauce = database.availableIngredients().get(0);
        Ingredient filling = database.availableIngredients().get(3);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        // Проверяем, что данные корректно установились
        Assertions.assertEquals(bun.getName(), burger.bun.getName());
        Assertions.assertEquals(2, burger.ingredients.size());

        // Проверяем цену
        float expectedPrice = bun.getPrice() * 2 + sauce.getPrice() + filling.getPrice();
        Assertions.assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }
}