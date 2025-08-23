import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

public class IngredientTest {

    public static Object[][] testData() {
        Object[][] testData = {
                {FILLING, "Котлета", 500},
                {FILLING, "Лук красный", 50},
                {FILLING, "Соленый огурец", 50},
                {SAUCE, "Кетчуп", 60},
                {SAUCE, "Майонез", 70},
                {SAUCE, "Кетчунез", 300}
        };
        return testData;
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void getNameTest(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        String actualName = ingredient.getName();
        assertEquals(name, actualName, "Имя не соотвествует заданному!");
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void getPriceTest(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        float actualPrice = ingredient.getPrice();
        assertEquals(price, actualPrice, "Цена не соотвествует заданной!");
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void getTypeTest(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        IngredientType actualType = ingredient.getType();
        assertEquals(type, actualType, "Тип не соотвествует заданному!");
    }

}
