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
    private final float ingredient1Price;
    private final float ingredient2Price;
    private final float expectedPrice;
    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient1;
    @Mock
    private Ingredient ingredient2;

    private Burger burger;

    public BurgerTest (float bunPrice, float ingredient1Price, float ingredient2Price, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredient1Price = ingredient1Price;
        this.ingredient2Price = ingredient2Price;
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
        when(ingredient1.getPrice()).thenReturn(ingredient1Price);
        when(ingredient2.getPrice()).thenReturn(ingredient2Price);

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
        burger.addIngredient(ingredient1);
        assertEquals("Размер списка должен увеличиться на 1",expectedSize + 1,burger.ingredients.size());

    }

    //Проверяем, что добавился именно нужный ингридиент
    @Test
    public void addIngredientCorrectTest () {
        burger.addIngredient(ingredient1);
        assertTrue("Список должен содержать добавленный ингридиент",burger.ingredients.contains(ingredient1));
    }
    //Проверяем что список ингридиентов уменьшился на 1
    @Test
    public void removeIngredientSizeTest () {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        int expectedSize = burger.ingredients.size();
        burger.removeIngredient(0);
        assertEquals("Размер списка должен уменьшиться на 1",expectedSize-1,burger.ingredients.size());
    }
    // Проверяем, что удалился нужный ингридиент
    @Test
    public void removeIngredientCorrectTest () {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);
        assertFalse("Первый ингридиент с индексом 0 должен быть удален",burger.ingredients.contains(ingredient1));
        assertTrue("Второй ингридиент с индексом 1 должен остаться в списке",burger.ingredients.contains(ingredient2));
    }
    // Проверяем, что размер списка ингридиентов не меняется при смене индексов ингридиентов
    @Test
    public void moveIngredientNotChangeSiseTest () {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        int expectedSize = burger.ingredients.size();
        burger.moveIngredient(0,1);
        assertEquals("Размер списка не должен измениться",expectedSize,burger.ingredients.size());
    }
    //  Проверяем, что ингредиент перемещается на правильную позицию
    @Test
    public void moveIngredientCorrectTest () {
        burger.addIngredient(ingredient1); //[0]
        burger.addIngredient(ingredient2); //[1]
        burger.moveIngredient(0,1);
        assertEquals("Ингридиент 2 должен быть с индексом 0",ingredient2,burger.ingredients.get(0));
        assertEquals("Ингридиент 1 должен быть с индексом 1",ingredient1,burger.ingredients.get(1));
    }
// Проверяем, ято цена бургера рассчитывается правильно
    @Test
    public void getPriceTest () {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        assertEquals("Цена бургера рассчитана неверно",expectedPrice,burger.getPrice(),0.001f);
    }



}
