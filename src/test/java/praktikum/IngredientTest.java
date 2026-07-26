package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    private static IngredientType type = IngredientType.SAUCE;
    private static String name = "Сальса";
    private static float price = 100.0F;
    Ingredient ingredient = new Ingredient(type, name, price);

    @Test
    @DisplayName("Получение названия ингредиента")
    public void getNameOfIngredientTest() {
        String actualName = ingredient.getName();
        assertEquals(name,
                    actualName,
                    String.format("Неверное название булочки: ожидалось \"%s\", фактически - \"%s\"", name, actualName)
        );
    }

    @Test
    @DisplayName("Поулчение типа ингредиента")
    public void getTypeOfIngredientTest() {
        IngredientType actualType = ingredient.getType();
        assertEquals(type,
                    actualType,
                    String.format("Неверный тип булочки: ожидалось \"%s\", фактически - \"%s\"", type, actualType)
        );
    }

    @Test
    @DisplayName("Поучение стоимости ингредиента")
    public void getPriceOfIngredientTest() {
        float actualPrice = ingredient.getPrice();
        assertEquals(price,
                    actualPrice,
                    String.format("Неверная стоимость булочки: ожидалось \"%f\", фактически - \"%f\"", price, actualPrice)
        );
    }

}