package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BurgerTest {
    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;
    @Mock
    private Ingredient secondIngredient;

    @BeforeEach
    public void setUp() {
        burger = new Burger();
    }
    @Test
    @DisplayName("Проверка корректности установки булочки")
    void testSetBuns() {
        burger.setBuns(bun);
            assertEquals(bun, burger.bun, "Булочка не установлена правильно");
        }

    @Test
    @DisplayName("Проверка корректности добавление ингредиента")
    void testAddIngredient() {
        burger.addIngredient(ingredient);
            assertTrue(burger.ingredients.contains(ingredient), "Ингредиенты не добавлены");
    }

    @Test
    @DisplayName("Проверка корректности удаление ингредиента")
    public void testRemoveIngredient() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertFalse(burger.ingredients.contains(ingredient), "Ингредиент не удален");
    }
    @Test
    @DisplayName("Проверка расчета цены бургера перемещение ингредиентов")
    public void testMoveIngredient() {
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);
        burger.moveIngredient(0, 1);

        assertAll("Проверка перемещения ингредиентов",
                () -> assertEquals(secondIngredient, burger.ingredients.get(0),
                        "Ингредиент на позиции 0 не соответствует ожидаемому"),
                () -> assertEquals(ingredient, burger.ingredients.get(1),
                        "Ингредиент на позиции 1 не соответствует ожидаемому")
        );
    }

    @Test
    @DisplayName("Проверка расчета цены бургера")
    public void testGetPrice() {
        Mockito.when(bun.getPrice()).thenReturn(100.0f);
        Mockito.when(ingredient.getPrice()).thenReturn(50.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        assertEquals(250.0f, burger.getPrice(), 0.0f,
                "Цена бургера рассчитана неправильно");
    }

    @Test
    @DisplayName("Проверка расчета цены бургера формирование чека")
    public void testGetReceipt() {
        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(bun.getPrice()).thenReturn(100.0f);
        Mockito.when(ingredient.getName()).thenReturn("hot sauce");
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient.getPrice()).thenReturn(50.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String expectedReceipt = String.format("(==== %s ====)%n= sauce %s =%n(==== %s ====)%n%nPrice: %f%n",
                "black bun", "hot sauce", "black bun", 250.0f);

        assertEquals(expectedReceipt, burger.getReceipt(),
                "Квитанция о бургере не соответствует ожидаемой");
    }
}