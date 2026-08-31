import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerTest {
    private final float bunPrice;
    private final float ingredientOnePrice;
    private final float ingredientTwoPrice;
    private final float expectedPrice;
    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredientOne;
    @Mock
    private Ingredient ingredientTwo;

    private Burger burger;

    public BurgerTest (float bunPrice, float ingredientOnePrice, float ingredientTwoPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientOnePrice = ingredientOnePrice;
        this.ingredientTwoPrice = ingredientTwoPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters( name = "bun = {0},ingr1 = {1},ingr2 = {2},expected = {3}")
    public static Collection<Object[]>data() {
        return Arrays.asList(new Object[][]{
                {100f,50f,25f,275f},
                {0f,0f,0f,0f},
                {10f,5f,0f,25f},
                {25.5f,10.25f,5.75f,67f}
        });
    }


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();

        when(bun.getPrice()).thenReturn(bunPrice);
        when(ingredientOne.getPrice()).thenReturn(ingredientOnePrice);
        when(ingredientTwo.getPrice()).thenReturn(ingredientTwoPrice);

    }

    @Test
    public void setBunsTest () {
        Bun anotherBun = mock(Bun.class);
        burger.setBuns(anotherBun);
        assertEquals("Булочка должна быть сохранена",anotherBun,burger.bun);

    }
    // Проверка на добавление ингридиента
    @Test
    public void addIngredientListSizeTest () {
        int expectedSize = burger.ingredients.size();
        burger.addIngredient(ingredientOne);
        assertEquals("Размер списка должен увеличиться на 1",expectedSize + 1,burger.ingredients.size());

    }

    //Проверяем, что добавился именно нужный ингридиент
    @Test
    public void addIngredientCorrectTest () {
        burger.addIngredient(ingredientOne);
        assertTrue("Список должен содержать добавленный ингридиент",burger.ingredients.contains(ingredientOne));
    }
    //Проверяем что список ингридиентов уменьшился на 1
    @Test
    public void removeIngredientSizeTest () {
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);
        int expectedSize = burger.ingredients.size();
        burger.removeIngredient(0);
        assertEquals("Размер списка должен уменьшиться на 1",expectedSize-1,burger.ingredients.size());
    }
    // Проверяем, что удалился нужный ингридиент
    @Test
    public void removeIngredientCorrectTest () {
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);
        burger.removeIngredient(0);
        assertFalse("Первый ингридиент с индексом 0 должен быть удален",burger.ingredients.contains(ingredientOne));
    }
    // Проверяем что нужный ингридиент не удаляется
    @Test
    public void remainsIngredientCorrectTest () {
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);
        burger.removeIngredient(0);
        assertTrue("Второй ингридиент с индексом 1 должен остаться в списке",burger.ingredients.contains(ingredientTwo));
    }
    // Проверяем, что размер списка ингридиентов не меняется при смене индексов ингридиентов
    @Test
    public void moveIngredientNotChangeSiseTest () {
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);
        int expectedSize = burger.ingredients.size();
        burger.moveIngredient(0,1);
        assertEquals("Размер списка не должен измениться",expectedSize,burger.ingredients.size());
    }
    //  Проверяем, что ингредиент перемещается на правильную позицию
    @Test
    public void moveIngredientOneCorrectTest () {
        burger.addIngredient(ingredientOne); //[0]
        burger.addIngredient(ingredientTwo); //[1]
        burger.moveIngredient(0,1);
        assertEquals("Ингридиент 1 должен быть с индексом 1",ingredientOne,burger.ingredients.get(1));
    }
    @Test
    public void moveIngredientTwoCorrectTest () {
        burger.addIngredient(ingredientOne); //[0]
        burger.addIngredient(ingredientTwo); //[1]
        burger.moveIngredient(0, 1);
        assertEquals("Ингридиент 2 должен быть с индексом 0", ingredientTwo, burger.ingredients.get(0));
    }
// Проверяем, ято цена бургера рассчитывается правильно
    @Test
    public void getPriceTest () {
        burger.setBuns(bun);
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);
        assertEquals("Цена бургера рассчитана неверно",expectedPrice,burger.getPrice(),0.001f);
    }
}
