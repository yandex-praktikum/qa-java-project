package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bunMock;
    @Mock
    private Ingredient ingredientMock1;
    @Mock
    private Ingredient ingredientMock2;

    @BeforeEach
    void setUp() {
        burger = new Burger();
        Mockito.lenient().when(bunMock.getName()).thenReturn("mock bun");
        Mockito.lenient().when(bunMock.getPrice()).thenReturn(100.0f);
        Mockito.lenient().when(ingredientMock1.getName()).thenReturn("mock sauce");
        Mockito.lenient().when(ingredientMock1.getPrice()).thenReturn(50.0f);
        Mockito.lenient().when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.lenient().when(ingredientMock2.getName()).thenReturn("mock cutlet");
        Mockito.lenient().when(ingredientMock2.getPrice()).thenReturn(150.0f);
        Mockito.lenient().when(ingredientMock2.getType()).thenReturn(IngredientType.FILLING);
    }

    @Test
    @DisplayName("setBuns устанавливает булочку")
    void shouldSetBun() {
        burger.setBuns(bunMock);
        assertNotNull(burger.bun);
        assertEquals("mock bun", burger.bun.getName());
    }

    @Test
    @DisplayName("addIngredient добавляет ингредиент")
    void shouldAddIngredient() {
        burger.addIngredient(ingredientMock1);
        assertEquals(1, burger.ingredients.size());
        assertEquals("mock sauce", burger.ingredients.get(0).getName());
    }

    @Test
    @DisplayName("removeIngredients удаляет ингредиент по индексу")
    void shouldRemoveByIndex() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals("mock cutlet", burger.ingredients.get(0).getName());
    }

    @Test
    @DisplayName("moveIngredients перемещает ингредиент")
    void shouldMoveIngredient() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.moveIngredient(1, 0);
        List<Ingredient> ingredients = burger.ingredients;
        assertEquals("mock cutlet", ingredients.get(0).getName());
        assertEquals("mock sauce", ingredients.get(1).getName());
    }

    @Test
    @DisplayName("getPrice корректно рассчитывает стоимость бургера")
    void shouldGetPriceCorrect() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        float expectedPrice = 100 * 2 + 50 + 150;
        assertEquals(expectedPrice, burger.getPrice());
    }

    @Test
    @DisplayName("getReceipt печатает чек корректного формата с информацией о бургере")
    void shouldGetCorrectReceipt() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        String expected = "(==== mock bun ====)\r\n= sauce mock sauce =\r\n= filling mock cutlet =\r\n(==== mock bun ====)\r\n\r\nPrice: 400,000000\r\n";
        String actual = burger.getReceipt();
        assertEquals(expected, actual);
    }
}
