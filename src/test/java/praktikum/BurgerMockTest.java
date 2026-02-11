package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerMockTest {

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    @Mock
    private Ingredient mockIngredient3;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBunsShouldSetBunReference() {
        burger.setBuns(mockBun);

        assertSame("Булочка должна быть установлена", mockBun, burger.bun);
    }

    @Test
    public void testAddIngredientShouldIncreaseListSize() {
        burger.addIngredient(mockIngredient1);

        assertEquals("После добавления ингредиента размер списка должен быть 1",
                1, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientShouldAddCorrectIngredient() {
        burger.addIngredient(mockIngredient1);

        assertSame("Добавленный ингредиент должен соответствовать переданному",
                mockIngredient1, burger.ingredients.get(0));
    }

    @Test
    public void testAddMultipleIngredientsShouldIncreaseSizeCorrespondingly() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        assertEquals("После добавления двух ингредиентов размер списка должен быть 2",
                2, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientShouldDecreaseListSize() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.removeIngredient(0);

        assertEquals("После удаления ингредиента размер списка должен быть 1",
                1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientShouldRemoveCorrectElement() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.removeIngredient(0);

        assertSame("После удаления первого ингредиента второй должен быть на позиции 0",
                mockIngredient2, burger.ingredients.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientWithInvalidIndexShouldThrowException() {
        burger.removeIngredient(0);
    }

    @Test
    public void testMoveIngredientShouldChangePosition() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.moveIngredient(0, 2);

        assertSame("Ингредиент с позиции 0 должен переместиться на позицию 2",
                mockIngredient1, burger.ingredients.get(2));
    }

    @Test
    public void testMoveIngredientShouldReorderOtherIngredients() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.moveIngredient(0, 2);

        assertSame("Ингредиент с позиции 1 должен стать на позицию 0",
                mockIngredient2, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientShouldMaintainListSize() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.moveIngredient(0, 2);

        assertEquals("Размер списка не должен измениться при перемещении",
                3, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredientToSamePositionShouldNotChangeOrder() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.moveIngredient(0, 0);

        assertSame("При перемещении на ту же позицию порядок не должен измениться",
                mockIngredient1, burger.ingredients.get(0));
    }

    // ТЕСТЫ ДЛЯ getPrice() - РАЗДЕЛЕНЫ ПО ОДНОЙ ПРОВЕРКЕ

    @Test
    public void testGetPriceShouldCallBunGetPrice() {
        when(mockBun.getPrice()).thenReturn(100.0f);
        burger.setBuns(mockBun);

        burger.getPrice();

        verify(mockBun, times(1)).getPrice();
    }

    @Test
    public void testGetPriceShouldCallIngredientGetPrice() {
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredient1.getPrice()).thenReturn(50.0f);
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        burger.getPrice();

        verify(mockIngredient1, times(1)).getPrice();
    }

    @Test
    public void testGetPriceShouldCalculateCorrectPriceWithBunAndIngredients() {
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredient1.getPrice()).thenReturn(50.0f);
        when(mockIngredient2.getPrice()).thenReturn(75.0f);
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        float price = burger.getPrice();
        float expectedPrice = (100.0f * 2) + 50.0f + 75.0f;

        assertEquals("Цена бургера с булочкой и двумя ингредиентами рассчитана неверно",
                expectedPrice, price, 0.001);
    }

    @Test
    public void testGetPriceWithOnlyBunShouldCalculateCorrectPrice() {
        when(mockBun.getPrice()).thenReturn(150.0f);
        burger.setBuns(mockBun);

        float price = burger.getPrice();

        assertEquals("Цена бургера только с булочкой должна быть равна цене булочки * 2",
                300.0f, price, 0.001);
    }

    @Test
    public void testGetPriceWithFreeBunShouldCalculateCorrectPrice() {
        when(mockBun.getPrice()).thenReturn(0.0f);
        when(mockIngredient1.getPrice()).thenReturn(100.0f);
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        float price = burger.getPrice();

        assertEquals("Цена бургера с бесплатной булочкой должна быть равна сумме ингредиентов",
                100.0f, price, 0.001);
    }

    // ТЕСТЫ ДЛЯ getReceipt() - РАЗДЕЛЕНЫ ПО ОДНОЙ ПРОВЕРКЕ

    @Test
    public void testGetReceiptShouldContainBunInfo() {
        when(mockBun.getName()).thenReturn("black bun");
        burger.setBuns(mockBun);

        String receipt = burger.getReceipt();

        assertTrue("Чек должен содержать информацию о булочке",
                receipt.contains("(==== black bun ====)"));
    }

    @Test
    public void testGetReceiptShouldContainSauceInfo() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getName()).thenReturn("hot sauce");
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        String receipt = burger.getReceipt();

        assertTrue("Чек должен содержать информацию о соусе",
                receipt.contains("= sauce hot sauce ="));
    }

    @Test
    public void testGetReceiptShouldContainFillingInfo() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getName()).thenReturn("cutlet");
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient2);

        String receipt = burger.getReceipt();

        assertTrue("Чек должен содержать информацию о начинке",
                receipt.contains("= filling cutlet ="));
    }

    @Test
    public void testGetReceiptShouldContainPrice() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(200.0f);
        burger.setBuns(mockBun);

        String receipt = burger.getReceipt();

        assertTrue("Чек должен содержать информацию о цене",
                receipt.contains("Price:"));
    }

    @Test
    public void testGetReceiptShouldNotBeNull() {
        when(mockBun.getName()).thenReturn("white bun");
        when(mockBun.getPrice()).thenReturn(100.0f);
        burger.setBuns(mockBun);

        String receipt = burger.getReceipt();

        assertNotNull("Чек не должен быть null", receipt);
    }

    @Test
    public void testGetReceiptShouldCallBunGetNameTwice() {
        when(mockBun.getName()).thenReturn("bun");
        burger.setBuns(mockBun);

        burger.getReceipt();

        verify(mockBun, times(2)).getName();
    }

    @Test
    public void testGetReceiptShouldCallBunGetPriceOnce() {
        when(mockBun.getName()).thenReturn("bun");
        when(mockBun.getPrice()).thenReturn(100.0f);
        burger.setBuns(mockBun);

        burger.getReceipt();

        verify(mockBun, times(1)).getPrice();
    }

    @Test(expected = NullPointerException.class)
    public void testGetPriceWithNoBunShouldThrowNullPointerException() {
        burger.addIngredient(mockIngredient1);

        burger.getPrice();
    }

    @Test
    public void testGetPriceWithEmptyBurgerShouldCalculateOnlyBunPrice() {
        when(mockBun.getPrice()).thenReturn(50.0f);
        burger.setBuns(mockBun);

        float price = burger.getPrice();

        assertEquals("Цена пустого бургера должна быть равна цене булочки * 2",
                100.0f, price, 0.001);
    }
}