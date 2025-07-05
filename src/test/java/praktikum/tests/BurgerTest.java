package praktikum.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredientCutlet;

    @Mock
    private Ingredient mockIngredientCheese;

    @Mock
    private Ingredient mockIngredientKetchup;

    private Burger burger;

    @Before
    public void init() {
        burger = new Burger();
    }

    // Тесты для метода setBuns()
    @Test
    public void setBunsValidBunShouldSetBunTest() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }

    @Test
    public void setBunsNullBunShouldSetNullTest() {
        burger.setBuns(null);
        assertNull(burger.bun);
    }

    // Тест списка ингредиентов
    @Test
    public void initialBurgerIngredientListShouldBeEmptyTest() {
        assertTrue(burger.ingredients.isEmpty());
    }

    // Тесты для метода addIngredient()
    @Test
    public void addIngredientValidIngredientShouldAddToListTest() {
        burger.addIngredient(mockIngredientCutlet);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addIngredientSeveralIngredientsShouldAddAllToListTest() {
        burger.addIngredient(mockIngredientCutlet);
        burger.addIngredient(mockIngredientCheese);
        burger.addIngredient(mockIngredientKetchup);
        assertEquals(3, burger.ingredients.size());
    }

    @Test
    public void addIngredientNullIngredientShouldAddNullTest() {
        burger.addIngredient(null);
        assertNull(burger.ingredients.get(0));
    }

    // Негативный тест для метода getPrice()
    @Test(expected = NullPointerException.class)
    public void getPriceNullBunShouldThrowExceptionTest() {
        burger.setBuns(null);
        burger.getPrice();
    }

    // Негативный тест для метода getReceipt()
    @Test(expected = NullPointerException.class)
    public void getReceiptNullBunShouldThrowExceptionTest() {
        burger.setBuns(null);
        burger.getReceipt();
    }
}
