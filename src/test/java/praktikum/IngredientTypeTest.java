package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTypeTest {

    @Test
    public void ingredientTypeShouldContainSauceAndFilling() {
        IngredientType[] types = IngredientType.values();
        assertEquals(2, types.length, "Количество типов ингредиентов неверное");
        assertEquals(IngredientType.SAUCE, types[0], "Первый тип должен быть SAUCE");
        assertEquals(IngredientType.FILLING, types[1], "Второй тип должен быть FILLING");

    }
}
