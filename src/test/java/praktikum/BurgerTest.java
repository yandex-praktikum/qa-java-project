package praktikum;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Юнит-тесты для класса Burger.
 * Каждый тест проверяет ровно одно утверждение
 */
@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    private Bun bun;

    @Mock
    private Ingredient sauce;
    @Mock
    private Ingredient filling;
    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    /**
     * Проверяем установку булки для бургера
     */
    @Test
    public void setBunShouldSetBun() {
        burger.setBuns(bun);
        assertSame(bun, burger.bun);
    }

    /**
     * Проверяем, что при добавлении ингредиента он попадает в список
     */
    @Test
    public void addIngredientShouldIncreaseSize() {
        burger.addIngredient(sauce);
        assertEquals(1, burger.ingredients.size());
    }
    @Test
    public void addIngredientShouldStoreSameReference() {
        burger.addIngredient(sauce);
        assertSame(sauce, burger.ingredients.get(0));
    }
    /**
     * Проверяет удаление ингредиента
     */
    @Test
    public void removeIngredientShouldDecreaseSize() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
    }
    @Test
    public void removeIngredientShouldRemoveExactElement() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.removeIngredient(0);
        assertSame(filling, burger.ingredients.get(0));
    }
    /**
     * Проверяет удаление ингредиента по несуществующему индексу
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientWithInvalidIndexShouldThrowException() {
        burger.addIngredient(sauce);
        burger.removeIngredient(5);
    }

    /**
     * Проверяет перемещение ингредиента на другую позицию
     */
    @Test
    public void moveIngredientShouldMoveIngredientToNewIndex() {
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);
        Ingredient third = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);
        burger.addIngredient(third);

        burger.moveIngredient(0, 2);

        assertEquals(Arrays.asList(second, third, first), burger.ingredients);
    }


    /**
     * Проверяет перемещение ингредиента на несуществующую позицию
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientWithInvalidIndexShouldThrowException() {
        burger.addIngredient(sauce);
        burger.moveIngredient(0, 5);
    }

    /**
     * Тесты расчёта цены
     */
    @Test
    public void getPriceWithoutIngredientsShouldReturnDoubleBunPrice() {
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);
        assertEquals(200f, burger.getPrice(), 0.001f);
    }

    /**
     * Тест чека используем SoftAssertions
     */
    @Test
    public void getReceiptShouldContainAllExpectedParts() {
        when(bun.getName()).thenReturn("red bun");
        when(bun.getPrice()).thenReturn(300f);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("chili sauce");
        when(sauce.getPrice()).thenReturn(300f);
        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getName()).thenReturn("dinosaur");
        when(filling.getPrice()).thenReturn(200f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String receipt = burger.getReceipt();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(receipt)
                .as("Чек должен содержать имя булки")
                .contains("red bun");
        softly.assertThat(countOccurrences(receipt, "red bun"))
                .as("Имя булки должно встречаться дважды (верх и низ)")
                .isEqualTo(2);
        softly.assertThat(receipt)
                .as("Чек должен содержать название соуса")
                .contains("chili sauce");
        softly.assertThat(receipt)
                .as("Чек должен содержать название начинки")
                .contains("dinosaur");
        softly.assertThat(receipt)
                .as("Чек должен содержать строку с ценой")
                .contains("Price:");
        softly.assertAll();
    }

    @Test
    public void getReceiptWithoutIngredientsShouldNotContainIngredientLines() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        String receipt = burger.getReceipt();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(receipt)
                .as("Чек должен содержать имя булки")
                .contains("black bun");
        softly.assertThat(receipt)
                .as("Чек должен содержать строку с ценой")
                .contains("Price:");
        softly.assertThat(receipt)
                .as("В чеке не должно быть строк с соусом")
                .doesNotContain("= sauce");
        softly.assertThat(receipt)
                .as("В чеке не должно быть строк с начинкой")
                .doesNotContain("= filling");
        softly.assertAll();
    }

    /** вспомогательный метод
     * Считает, сколько раз подстрока встречается в строке
     * Нужен для проверки, что имя булки напечатано дважды
     */
    private int countOccurrences(String source, String target) {
        int count = 0;
        int index = 0;
        while ((index = source.indexOf(target, index)) != -1) {
            count++;
            index += target.length();
        }
        return count;
    }
}
