package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @Test
    void testCreateFilling() {
        Ingredient ingredient = new Ingredient(
                IngredientType.FILLING,
                "Говяжий метеорит (отбивная)",
                3000
        );

        assertEquals(IngredientType.FILLING, ingredient.getType());
        assertEquals("Говяжий метеорит (отбивная)", ingredient.getName());
        assertEquals(3000, ingredient.getPrice());
    }

    @Test
    void testCreateSauce() {
        Ingredient ingredient = new Ingredient(
                IngredientType.SAUCE,
                "Соус Spicy-X",
                90
        );

        assertEquals(IngredientType.SAUCE, ingredient.getType());
        assertEquals("Соус Spicy-X", ingredient.getName());
        assertEquals(90, ingredient.getPrice());
    }

    @Test
    void testCreateCheese() {
        Ingredient ingredient = new Ingredient(
                IngredientType.FILLING,
                "Сыр с астероидной плесенью",
                4142
        );

        assertEquals(IngredientType.FILLING, ingredient.getType());
        assertEquals("Сыр с астероидной плесенью", ingredient.getName());
        assertEquals(4142, ingredient.getPrice());
    }

    @Test
    void testDirectFields() {
        Ingredient ingredient = new Ingredient(
                IngredientType.SAUCE,
                "Соус традиционный галактический",
                15
        );

        assertEquals(IngredientType.SAUCE, ingredient.type);
        assertEquals("Соус традиционный галактический", ingredient.name);
        assertEquals(15, ingredient.price);
    }

    @Test
    void testGetMethods() {
        Ingredient ingredient = new Ingredient(
                IngredientType.FILLING,
                "Мясо бессмертных моллюсков Protostomia",
                1337
        );

        assertEquals("Мясо бессмертных моллюсков Protostomia", ingredient.getName());
        assertEquals(1337, ingredient.getPrice());
        assertEquals(IngredientType.FILLING, ingredient.getType());
    }
}
