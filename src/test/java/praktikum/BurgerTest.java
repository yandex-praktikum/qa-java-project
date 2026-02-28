package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    @Mock
    private Ingredient mockIngredient3;

    private Burger burger;

    @BeforeEach
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun, "Булочка должна быть установлена");
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockIngredient1);

        assertEquals(1, burger.ingredients.size(), "Должен быть добавлен 1 ингредиент");
        assertEquals(mockIngredient1, burger.ingredients.get(0), "Добавленный ингредиент должен совпадать");
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size(), "После удаления должен остаться один ингредиент");
        assertEquals(mockIngredient2, burger.ingredients.get(0), "Оставшийся ингредиент должен быть вторым");
    }

    @Test
    public void testRemoveIngredientInvalidIndex() {
        burger.addIngredient(mockIngredient1);
        assertThrows(IndexOutOfBoundsException.class,
                () -> burger.removeIngredient(5));
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(mockIngredient1); // index 0
        burger.addIngredient(mockIngredient2); // index 1
        burger.addIngredient(mockIngredient3); // index 2

        burger.moveIngredient(2, 0);
        burger.moveIngredient(1, 1);

        assertEquals(mockIngredient3, burger.ingredients.get(0));
        assertEquals(mockIngredient1, burger.ingredients.get(1));
        assertEquals(mockIngredient2, burger.ingredients.get(2));
    }

    @Test
    public void testMoveIngredientInvalidIndex() {
        burger.addIngredient(mockIngredient1);
        assertThrows(IndexOutOfBoundsException.class,
                () -> burger.moveIngredient(0, 5));
    }

    @Test
    public void testGetPrice() {
        when(mockBun.getPrice()).thenReturn(50.0f);
        when(mockIngredient1.getPrice()).thenReturn(30.0f);
        when(mockIngredient2.getPrice()).thenReturn(40.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        float expectedPrice = 50.0f * 2 + 30.0f + 40.0f;
        float actualPrice = burger.getPrice();

        assertEquals(expectedPrice, actualPrice, "Цена рассчитана неверно");

        verify(mockBun, times(1)).getPrice();
        verify(mockIngredient1, times(1)).getPrice();
        verify(mockIngredient2, times(1)).getPrice();
        verify(mockIngredient3, never()).getPrice();
    }

    @Test
    public void testGetPriceWithNoIngredients() {
        when(mockBun.getPrice()).thenReturn(50.0f);

        burger.setBuns(mockBun);

        float expectedPrice = 50.0f * 2;
        float actualPrice = burger.getPrice();

        assertEquals(expectedPrice, actualPrice);

        verify(mockBun, times(1)).getPrice();
        verify(mockIngredient1, never()).getPrice();
        verify(mockIngredient2, never()).getPrice();
    }

    @Test
    public void testGetReceipt() {
        when(mockBun.getName()).thenReturn("MockBun");
        when(mockBun.getPrice()).thenReturn(50.0f);

        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getName()).thenReturn("MockSauce");
        when(mockIngredient1.getPrice()).thenReturn(30.0f);

        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getName()).thenReturn("MockFilling");
        when(mockIngredient2.getPrice()).thenReturn(40.0f);
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        float expectedPrice = 50.0f * 2 + 30.0f + 40.0f;

        String expectedReceipt =
                String.format("(==== %s ====)%n", "MockBun") +
                String.format("= %s %s =%n", "sauce", "MockSauce") +
                String.format("= %s %s =%n", "filling", "MockFilling") +
                String.format("(==== %s ====)%n", "MockBun") +
                String.format("%nPrice: %f%n", expectedPrice);

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt, "Чек сформирован неверно");

        verify(mockBun, times(2)).getName();
        verify(mockBun, times(1)).getPrice();

        verify(mockIngredient1, times(1)).getType();
        verify(mockIngredient1, times(1)).getName();
        verify(mockIngredient1, times(1)).getPrice();

        verify(mockIngredient2, times(1)).getType();
        verify(mockIngredient2, times(1)).getName();
        verify(mockIngredient2, times(1)).getPrice();
    }

    @Test
    public void testGetReceiptWithNoIngredients() {
        when(mockBun.getName()).thenReturn("MockBun");
        when(mockBun.getPrice()).thenReturn(50.0f);

        burger.setBuns(mockBun);

        float expectedPrice = 50.0f * 2;

        String expectedReceipt =
                String.format("(==== %s ====)%n", "MockBun") +
                String.format("(==== %s ====)%n", "MockBun") +
                String.format("%nPrice: %f%n", expectedPrice);

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt);

        verify(mockBun, times(2)).getName();
        verify(mockBun, times(1)).getPrice();

        verify(mockIngredient1, never()).getType();
        verify(mockIngredient1, never()).getName();
        verify(mockIngredient2, never()).getType();
        verify(mockIngredient2, never()).getName();
        verify(mockIngredient3, never()).getType();
        verify(mockIngredient3, never()).getName();
    }
}