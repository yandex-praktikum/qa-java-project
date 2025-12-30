import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BurgerTests {

    @Mock
    Bun bunMock;

    @Mock
    Ingredient ingredientMock1;

    @Mock
    Ingredient ingredientMock2;

    @Mock
    List<Ingredient> ingredientsListMock;

    @Test
    @DisplayName("Проверка сеттинга булочки с моками")
    void setsBunsField() {
        Burger burger = new Burger();
        burger.setBuns(bunMock);

        assertThat(burger.bun).isSameAs(bunMock);
    }

    @Test
    @DisplayName("Проверка добавления нового ингредиента с моками")
    void addIngredientDelegatesToIngredientsList() {
        Burger burger = new Burger();
        burger.ingredients = ingredientsListMock;

        burger.addIngredient(ingredientMock1);

        verify(ingredientsListMock, times(1)).add(ingredientMock1);
        verifyNoMoreInteractions(ingredientsListMock);
    }

    @Test
    @DisplayName("Проверка удаление ингредиента с моками")
    void removeIngredientDelegatesToIngredientsList() {
        Burger burger = new Burger();
        burger.ingredients = ingredientsListMock;

        burger.removeIngredient(2);

        verify(ingredientsListMock, times(1)).remove(2);
        verifyNoMoreInteractions(ingredientsListMock);
    }

    @Test
    @DisplayName("Проверка перемещения интгредиента по индексу после удаления с моками")
    void moveIngredientRemovesThenAddsToNewIndex() {
        Burger burger = new Burger();
        burger.ingredients = ingredientsListMock;

        when(ingredientsListMock.remove(1)).thenReturn(ingredientMock1);

        burger.moveIngredient(1, 0);

        InOrder inOrder = inOrder(ingredientsListMock);
        inOrder.verify(ingredientsListMock).remove(1);
        inOrder.verify(ingredientsListMock).add(0, ingredientMock1);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    @DisplayName("Проверка получения цены бургера со стабами")
    void getPriceCalculatesBunDoublePlusIngredientsSum() {
        Burger burger = new Burger();
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        when(bunMock.getPrice()).thenReturn(10.0f);
        when(ingredientMock1.getPrice()).thenReturn(1.25f);
        when(ingredientMock2.getPrice()).thenReturn(3.00f);

        float actual = burger.getPrice();

        assertThat(actual).isCloseTo(24.25f, org.assertj.core.data.Offset.offset(0.0001f));
    }

    @Test
    @DisplayName("Проверка получения рецепта со стабами")
    void getReceiptBuildsCorrectReceiptString() {
        Burger burger = new Burger();
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        when(bunMock.getName()).thenReturn("black bun");
        when(bunMock.getPrice()).thenReturn(10.0f);

        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock1.getName()).thenReturn("spicy sauce");
        when(ingredientMock1.getPrice()).thenReturn(1.25f);

        when(ingredientMock2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock2.getName()).thenReturn("cutlet");
        when(ingredientMock2.getPrice()).thenReturn(3.00f);

        float expectedPrice = 10.0f * 2 + 1.25f + 3.00f;

        String expected = ""
                + String.format("(==== %s ====)%n", "black bun")
                + String.format("= %s %s =%n", "sauce", "spicy sauce")
                + String.format("= %s %s =%n", "filling", "cutlet")
                + String.format("(==== %s ====)%n", "black bun")
                + String.format("%nPrice: %f%n", expectedPrice);

        String actual = burger.getReceipt();

        assertThat(actual).isEqualTo(expected);
    }
}
