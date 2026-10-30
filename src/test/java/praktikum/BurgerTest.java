package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("Тесты класса Burger")
public class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient sauce;

    @Mock
    private Ingredient filling;

    private Burger burger;

    @BeforeEach
    public void setUp() {
        burger = new Burger();

        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100.0f);

        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("hot sauce");
        when(sauce.getPrice()).thenReturn(100.0f);

        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getName()).thenReturn("cutlet");
        when(filling.getPrice()).thenReturn(200.0f);
    }

    @Test
    @DisplayName("setBuns сохраняет переданную булочку")
    public void setBunsStoresBun() {
        burger.setBuns(bun);

        assertSame(bun, burger.bun);
    }

    @Test
    @DisplayName("addIngredient добавляет ингредиент в список")
    public void addIngredientAddsIngredient() {
        burger.addIngredient(sauce);

        assertEquals(1, burger.ingredients.size());
        assertSame(sauce, burger.ingredients.get(0));
    }

    @Test
    @DisplayName("removeIngredient удаляет ингредиент по индексу")
    public void removeIngredientRemovesByIndex() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertSame(filling, burger.ingredients.get(0));
    }

    @Test
    @DisplayName("moveIngredient перемещает ингредиент на новый индекс")
    public void moveIngredientMovesIngredient() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.moveIngredient(0, 1);

        assertSame(filling, burger.ingredients.get(0));
        assertSame(sauce, burger.ingredients.get(1));
    }

    @Test
    @DisplayName("getPrice считает две булочки и все ингредиенты")
    public void getPriceCalculatesTwoBunsAndIngredients() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        float expected = 100.0f * 2 + 100.0f + 200.0f;

        assertEquals(expected, burger.getPrice());
        verify(bun).getPrice();
        verify(sauce).getPrice();
        verify(filling).getPrice();
    }

    @Test
    @DisplayName("getPrice для бургера без ингредиентов равен цене двух булочек")
    public void getPriceForBunOnly() {
        burger.setBuns(bun);

        assertEquals(200.0f, burger.getPrice());
    }

    @Test
    @DisplayName("getReceipt содержит булочки, ингредиенты и цену")
    public void getReceiptContainsBunsIngredientsAndPrice() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String receipt = burger.getReceipt();

        assertEquals(
                """
                (==== black bun ====)
                = sauce hot sauce =
                = filling cutlet =
                (==== black bun ====)

                Price: 500.000000
                """,
                receipt
        );

        verify(bun, org.mockito.Mockito.times(2)).getName();
        verify(sauce).getType();
        verify(sauce).getName();
        verify(filling).getType();
        verify(filling).getName();
    }

    @Test
    @DisplayName("removeIngredient с некорректным индексом бросает исключение")
    public void removeIngredientThrowsForInvalidIndex() {
        burger.addIngredient(sauce);

        assertThrows(IndexOutOfBoundsException.class,
                () -> burger.removeIngredient(1));
    }
}