package praktikum;

import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.mockito.Mockito.when;

/**
 * Фабрика для создания моков Bun и Ingredient с предустановленными значениями
 */
public class MockFactory {

    public static Bun createBunMock(String name, float price) {
        Bun bun = Mockito.mock(Bun.class);
        when(bun.getName()).thenReturn(name);
        when(bun.getPrice()).thenReturn(price);
        return bun;
    }

    public static Ingredient createIngredientMock(IngredientType type, String name, float price) {
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        when(ingredient.getType()).thenReturn(type);
        when(ingredient.getName()).thenReturn(name);
        when(ingredient.getPrice()).thenReturn(price);
        return ingredient;
    }

    public static Ingredient createSauceMock(String name, float price) {
        return createIngredientMock(IngredientType.SAUCE, name, price);
    }

    public static Ingredient createFillingMock(String name, float price) {
        return createIngredientMock(IngredientType.FILLING, name, price);
    }

    // Предустановленные моки с понятными названиями
    public static Bun createDefaultBun() {
        return createBunMock("black bun", 100.0f);
    }

    public static Ingredient createHotSauce() {
        return createSauceMock("hot sauce", 50.0f);
    }

    public static Ingredient createCutlet() {
        return createFillingMock("cutlet", 150.0f);
    }

    public static Ingredient createChiliSauce() {
        return createSauceMock("chili sauce", 75.0f);
    }

    public static Ingredient createCheese() {
        return createFillingMock("cheese", 80.0f);
    }
}