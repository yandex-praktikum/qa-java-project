package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    @Mock
    private Ingredient secondIngredient;

    private Burger burger;

    @BeforeEach
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void getPriceShouldReturnDoubleBunPrice() {
        Mockito.when(bun.getPrice()).thenReturn(10f);
        burger.setBuns(bun);
        float actual = burger.getPrice();
        assertEquals(20f, actual, "Цена бургера должна включать стоимость двух булочек");
        Mockito.verify(bun, Mockito.times(1)).getPrice();
    }

    @Test
    public void getPriceShouldIncludeIngredientPrice() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        Mockito.when(bun.getPrice()).thenReturn(10f);
        Mockito.when(ingredient.getPrice()).thenReturn(10f);
        float actual = burger.getPrice();
        assertEquals(30f, actual, "Цена бургера должна включать две булочки и ингредиент");
        Mockito.verify(ingredient, Mockito.times(1)).getPrice();
    }

    @Test
    public void removeIngredientShouldRemoveIngredient() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty(), "Ингредиент не был удален из бургера");
    }

    @Test
    public void moveIngredientShouldMoveIngredient() {
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);
        burger.moveIngredient(0, 1);
        assertEquals(ingredient, burger.ingredients.get(1),
                "Первый ингредиент не был перемещен на позицию с индексом 1");
        assertEquals(secondIngredient, burger.ingredients.get(0),
                "Второй ингредиент должен находиться на позиции с индексом 0");
        assertEquals(2, burger.ingredients.size(),
                "Количество ингредиентов изменилось после перемещения");
    }
}