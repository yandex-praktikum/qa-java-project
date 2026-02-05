import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import org.assertj.core.api.SoftAssertions;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)

public class BurgerTest {

    private Burger burger;

    @Mock
    Bun mockBun;

    @Mock
    Ingredient mockIngredientFirst;

    @Mock
    Ingredient mockIngredientSecond;

    @Mock
    Ingredient mockIngredientThird;


    private static final int FIRST_POSITION = 0;
    private static final int SECOND_POSITION = 1;
    private static final int THIRD_POSITION = 2;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(mockBun);
        assertEquals("Значение поля bun соответсвует объекту Bun", mockBun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(mockIngredientFirst);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients)
                .as("Ингредиент должен добавиться в список ингредиентов")
                .hasSize(1);

        softly.assertThat(burger.ingredients.get(0))
                .as("Добавленный ингредиент должен совпадать с mock-ингредиентом")
                .isEqualTo(mockIngredientFirst);

        softly.assertAll();
    }
@Test
public void removeIngredientShouldReduceSize() {

    burger.ingredients.add(mockIngredientFirst);
    burger.ingredients.add(mockIngredientSecond);
    burger.ingredients.add(mockIngredientThird);

    burger.removeIngredient(SECOND_POSITION);

    assertEquals("Должно остаться 2 ингредиента", 2, burger.ingredients.size());
}

    @Test
    public void removeIngredientShouldKeepFirstIngredient() {

        burger.ingredients.add(mockIngredientFirst);
        burger.ingredients.add(mockIngredientSecond);
        burger.ingredients.add(mockIngredientThird);

        burger.removeIngredient(SECOND_POSITION);

        assertEquals("Первый ингредиент должен остаться", mockIngredientFirst, burger.ingredients.get(FIRST_POSITION));
    }

    @Test
    public void removeIngredientShouldNotMoveSecondIngredient() {

        burger.ingredients.add(mockIngredientFirst);
        burger.ingredients.add(mockIngredientSecond);
        burger.ingredients.add(mockIngredientThird);

        burger.removeIngredient(THIRD_POSITION);
        assertEquals("Второй ингредиент должен остаться", mockIngredientSecond, burger.ingredients.get(SECOND_POSITION));
    }

    @Test
    public void removeIngredientShouldNotContainRemovedIngredient() {

        burger.ingredients.add(mockIngredientFirst);
        burger.ingredients.add(mockIngredientSecond);
        burger.ingredients.add(mockIngredientThird);

        burger.removeIngredient(SECOND_POSITION);
        assertFalse("Второй ингредиент должен быть удален", burger.ingredients.contains(mockIngredientSecond));
    }
    @Test
    public void removeIngredientShouldMoveLastIngredient() {

        burger.ingredients.add(mockIngredientFirst);
        burger.ingredients.add(mockIngredientSecond);
        burger.ingredients.add(mockIngredientThird);

        burger.removeIngredient(SECOND_POSITION);
        assertEquals("Последний ингредиент должен сдвинуться", mockIngredientThird, burger.ingredients.get(SECOND_POSITION));
    }

    @Test
    public void getPriceTest() {
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredientFirst.getPrice()).thenReturn(50.0f);
        when(mockIngredientSecond.getPrice()).thenReturn(30.0f);

        burger.bun = mockBun;
        burger.ingredients.add(mockIngredientFirst);
        burger.ingredients.add(mockIngredientSecond);

        float actualPrice = burger.getPrice();

        assertEquals("Цена должна быть рассчитана правильно", 280.0f, actualPrice, 0.01f);
    }

    @Test
    public void getPriceTest_CallsBunGetPriceOnce() {
        when(mockBun.getPrice()).thenReturn(100.0f);

        burger.bun = mockBun;

        burger.getPrice();

        verify(mockBun, times(1)).getPrice();
    }

    @Test
    public void getPriceTest_CallsFirstIngredientGetPriceOnce() {
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredientFirst.getPrice()).thenReturn(50.0f);

        burger.bun = mockBun;
        burger.ingredients.add(mockIngredientFirst);

        burger.getPrice();

        verify(mockIngredientFirst, times(1)).getPrice();
    }

    @Test
    public void getPriceTest_CallsSecondIngredientGetPriceOnce() {
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredientSecond.getPrice()).thenReturn(30.0f);

        burger.bun = mockBun;
        burger.ingredients.add(mockIngredientSecond);

        burger.getPrice();

        verify(mockIngredientSecond, times(1)).getPrice();
    }

    @Test
    public void getReceiptTest() {

        burger.bun = mockBun;
        burger.ingredients.add(mockIngredientFirst);
        burger.ingredients.add(mockIngredientSecond);

        Burger burgerSpy = Mockito.spy(burger);

        when(mockBun.getName()).thenReturn("white bun");
        when(mockIngredientFirst.getType()).thenReturn(SAUCE);
        when(mockIngredientFirst.getName()).thenReturn("chili sauce");
        when(mockIngredientSecond.getType()).thenReturn(FILLING);
        when(mockIngredientSecond.getName()).thenReturn("dinosaur");
        when(burgerSpy.getPrice()).thenReturn(500.0f);


        String actualReceipt = burgerSpy.getReceipt();

        String expectedReceipt = String.format(

                "(==== %s ====)%n" +
                "= %s %s =%n" +
                "= %s %s =%n" +
                "(==== %s ====)%n%n" +
                "Price: %f%n",
                "white bun",
                "sauce", "chili sauce",
                "filling", "dinosaur",
                "white bun",
                500.0f
        );

        assertEquals("Чек должен соответствовать ожидаемому формату", expectedReceipt, actualReceipt);

    }
    @Test
    public void moveIngredient_FirstToThird() {
        burger.ingredients.add(mockIngredientFirst);
        burger.ingredients.add(mockIngredientSecond);
        burger.ingredients.add(mockIngredientThird);

        burger.moveIngredient(0, 2);

        List<Ingredient> expected = Arrays.asList(mockIngredientSecond, mockIngredientThird, mockIngredientFirst);
        assertEquals(expected, burger.ingredients);
    }

    @Test
    public void moveIngredient_ThirdToFirst() {
        burger.ingredients.add(mockIngredientFirst);
        burger.ingredients.add(mockIngredientSecond);
        burger.ingredients.add(mockIngredientThird);

        burger.moveIngredient(2, 0);

        List<Ingredient> expected = Arrays.asList(mockIngredientThird, mockIngredientFirst, mockIngredientSecond);
        assertEquals(expected, burger.ingredients);
    }

    @Test
    public void moveIngredient_SecondToThird() {
        burger.ingredients.add(mockIngredientFirst);
        burger.ingredients.add(mockIngredientSecond);
        burger.ingredients.add(mockIngredientThird);

        burger.moveIngredient(1, 2);

        List<Ingredient> expected = Arrays.asList(mockIngredientFirst, mockIngredientThird, mockIngredientSecond);
        assertEquals(expected, burger.ingredients);
    }
}