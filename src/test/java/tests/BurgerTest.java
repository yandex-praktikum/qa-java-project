package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BurgerTest {

    private Burger burger;
    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient1;
    @Mock
    private Ingredient ingredient2;

    @BeforeEach
    public void setUp() {
        burger = new Burger();
    }

    @Test
    void setBunsValueNotNull() {
        burger.setBuns(bun);
        assertThat(burger.bun, notNullValue());
    }

    @Test
    void addIngredientValueAddInList() {
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    void removeIngredientValueRemoveFromList() {
        burger.addIngredient(ingredient1);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    void moveIngredientValueGetNewIndex() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        assertEquals(1, burger.ingredients.indexOf(ingredient1));
    }

    @Test
    void getPriceShouldReturnBurgerPrice() {
        Mockito.when(bun.getPrice()).thenReturn(10.00f);
        Mockito.when(ingredient1.getPrice()).thenReturn(5.00f);
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        assertEquals(25.00f,burger.getPrice(),0.001f);
    }

    @Test
    void getReceiptShouldReturnReceipt() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(bun.getPrice()).thenReturn(10.00f);
        Mockito.when(ingredient1.getPrice()).thenReturn(5.00f);
        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient1.getName()).thenReturn("hot sauce");
        assertThat(burger.getReceipt(),containsString("black bun"));
        assertThat(burger.getReceipt(),containsString("hot sauce"));
    }
}