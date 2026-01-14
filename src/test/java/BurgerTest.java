import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

@ExtendWith(MockitoExtension.class)
class BurgerTest {

    @Mock
    Burger burger;
    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;

    @ParameterizedTest
    @CsvSource({
            "FILLING, cutlet, 100.0",
            "SAUCE, hot sauce, 100.0",
            "FILLING, dinosaur, 200.0",
            "SAUCE, sour cream, 15.0",
            "FILLING, sausage, 300.0",
            "SAUCE, chili sauce, 300.0"
    })
    void testAddIngredient(String typeStr, String name, float price) {
        IngredientType type = IngredientType.valueOf(typeStr);
        Mockito.when(ingredient.getType()).thenReturn(type);
        Mockito.when(ingredient.getName()).thenReturn(name);
        Mockito.when(ingredient.getPrice()).thenReturn(price);
        burger.addIngredient(ingredient);
        assertAll("Проверка добавленного ингредиента",
                () -> assertEquals(type, ingredient.getType(), "Тип ингредиента не совпадает"),
                () -> assertEquals(name, ingredient.getName(), "Название ингредиента не совпадает"),
                () -> assertEquals(price, ingredient.getPrice(), "Цена ингредиента не совпадает")
        );
    }

    @Test
    void testRemoveIngredient() {
        ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100.0f);
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        Mockito.verify(burger).removeIngredient(0);
    }

    @Test
    public void testMoveIngredient() {
        ingredient = new Ingredient(IngredientType.FILLING, "cutlet", 100.0f);
        burger.addIngredient(ingredient);
        burger.moveIngredient(0, 1);
        Mockito.verify(burger).moveIngredient(0, 1);
    }

    @Test
    void testGetPrice() {
        burger = new Burger();
        burger.setBuns(bun);
        Mockito.when(ingredient.getPrice()).thenReturn(100.0f);
        burger.addIngredient(ingredient);
        float totalPrice = burger.getPrice();
        assertEquals(100.0f, totalPrice);
    }


    @Test
    void testGetReceipt() {
        Mockito.when(bun.getName()).thenReturn("bun");
        Mockito.when(bun.getPrice()).thenReturn(300.0F);

        Ingredient ingredient0 = mock(Ingredient.class);
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);

        Mockito.when(ingredient0.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredient0.getName()).thenReturn("ingredient0");
        Mockito.when(ingredient0.getPrice()).thenReturn(100.0F);
        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredient1.getName()).thenReturn("ingredient1");
        Mockito.when(ingredient1.getPrice()).thenReturn(200.0F);
        Mockito.when(ingredient2.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient2.getName()).thenReturn("ingredient2");
        Mockito.when(ingredient2.getPrice()).thenReturn(100.0F);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient0);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        String expectedReceipt = String.format(
                "(==== bun ====)%n" +
                "= filling ingredient0 =%n" +
                "= filling ingredient1 =%n" +
                "= sauce ingredient2 =%n" +
                "(==== bun ====)%n" +
                "%nPrice: %f%n",
                1000.0F
        );
        String actualReceipt = burger.getReceipt();
        assertEquals(expectedReceipt, actualReceipt,
                String.format("Некорректный чек. Ожидалось: %s, Пришло: %s", expectedReceipt, actualReceipt));
    }
}