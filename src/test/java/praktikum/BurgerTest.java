package praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    // Тест названия
    public static final String BUN_NAME = "Черная булка";
    public static final String FILLING_NAME = "Динозавр";
    // Тест цены
    public static final float BUN_PRICE = 100f;
    public static final float INGREDIENT_PRICE = 300f;
    // Индекс расположение продуктов
    public static final int FIRST_INDEX = 0;
    public static final int SECOND_INDEX = 1;

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @After
    public void tearDown(){
        burger = null;
    }


    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        assertNotNull("Булочка установлена", burger.bun);
    }

    @Test
    public void testSetSelectedBuns() {
        burger.setBuns(bun);
        assertEquals("Установлена выбранная булочка", bun, burger.bun );
    }

    @Test
    public void testAddIngredient() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        assertNotNull("Добавили ингредиент", burger.ingredients.contains(ingredient));
    }

    @Test
    public void testAddOneIngredient() {
        int initialSize = burger.ingredients.size();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        assertEquals("Размер списка увеличился на 1", initialSize +1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertTrue("Удалили ингредиент", burger.ingredients.isEmpty());
    }

    @Test
    public void testMoveIngredientToDifferentPosition() {
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(FIRST_INDEX, SECOND_INDEX);
        assertEquals("После перемещения ингредиенты должны поменяться метами",
                ingredient1, burger.ingredients.get(SECOND_INDEX));
    }

    @Test
    public void testIngredientsCountAfterMove() {
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        int initialSize = burger.ingredients.size();

        burger.moveIngredient(FIRST_INDEX, SECOND_INDEX);
        assertEquals("После перемещения количество ингредиентов не должно изменяться",
                initialSize, burger.ingredients.size());
    }

    @Test
    public void testGetReceipt() {
        when(bun.getName()).thenReturn(BUN_NAME);
        when(bun.getPrice()).thenReturn(BUN_PRICE);
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient.getName()).thenReturn(FILLING_NAME);
        when(ingredient.getPrice()).thenReturn(INGREDIENT_PRICE);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String expectedReceipt = String.format("(==== %s ====)%n",BUN_NAME)
                + String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(), FILLING_NAME)
                + String.format("(==== %s ====)%n", BUN_NAME)
                + String.format("%nPrice: %f%n", burger.getPrice());

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}