package ru.practicum.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.MockitoAnnotations;
import praktikum.*;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {

    private final float bunPrice;
    private final float[] ingredientPrices;
    private final float expectedTotal;

    public BurgerPriceParameterizedTest(float bunPrice, float[] ingredientPrices, float expectedTotal) {
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedTotal = expectedTotal;
    }

    @Parameterized.Parameters(name = "bun={0}, ingredients={1}, expected={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {10.0f, new float[]{}, 20.0f},                    // только булки (две)
                {5.5f, new float[]{1.0f}, 12.0f},                 // булки + 1 ингредиент
                {3.0f, new float[]{0.5f, 1.5f, 2.0f}, 10.0f}      // несколько ингредиентов
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetPriceVariousCombinations() {
        // Настраиваем бургер с моком булочки и стабами ингредиентов
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bun);

        for (float p : ingredientPrices) {
            Ingredient ing = mock(Ingredient.class);
            when(ing.getPrice()).thenReturn(p);
            burger.addIngredient(ing);
        }

        assertEquals(expectedTotal, burger.getPrice(), 1e-6f);
    }
}