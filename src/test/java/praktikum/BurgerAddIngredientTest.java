package praktikum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class BurgerAddIngredientTest {

    private Burger burger;

    @Mock
    private Ingredient mockIngredientFirst;

    @Mock
    private Ingredient mockIngredientSecond;

    @Mock
    private Ingredient mockIngredientThird;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void addIngredientShouldIncreaseListSize() {
        int initialSize = burger.ingredients.size();
        burger.addIngredient(mockIngredientFirst);
        assertEquals("Размер списка должен увеличиться на 1", initialSize + 1, burger.ingredients.size());
    }

    @Test
    public void addIngredientShouldContainAddedIngredient() {
        burger.addIngredient(mockIngredientFirst);
        assertTrue("Список должен содержать добавленный ингредиент", burger.ingredients.contains(mockIngredientFirst));
    }

    @Test
    public void addIngredientShouldAddMultipleIngredients() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);
        assertEquals("Размер списка должен быть 2", 2, burger.ingredients.size());
    }

    @Test
    public void addIngredientShouldAddIngredientsInCorrectOrder() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);
        burger.addIngredient(mockIngredientThird);
        assertEquals("Первый элемент должен быть mockIngredientFirst", mockIngredientFirst, burger.ingredients.get(0));
    }

    @Test
    public void addIngredientShouldAddIngredientsInCorrectOrderSecond() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);
        burger.addIngredient(mockIngredientThird);
        assertEquals("Второй элемент должен быть mockIngredientSecond", mockIngredientSecond, burger.ingredients.get(1));
    }

    @Test
    public void addIngredientShouldAddIngredientsInCorrectOrderThird() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);
        burger.addIngredient(mockIngredientThird);
        assertEquals("Третий элемент должен быть mockIngredientThird", mockIngredientThird, burger.ingredients.get(2));
    }

    @Test
    public void addIngredientShouldAddIngredientWithNullFieldsSize() {
        Ingredient nullIngredient = new Ingredient(null, null, 0f);
        burger.addIngredient(nullIngredient);
        assertEquals("Размер списка должен быть 1", 1, burger.ingredients.size());
    }

    @Test
    public void addIngredientShouldAddIngredientWithNullType() {
        Ingredient nullIngredient = new Ingredient(null, null, 0f);
        burger.addIngredient(nullIngredient);
        assertNull("Тип ингредиента должен быть null", burger.ingredients.get(0).getType());
    }

    @Test
    public void addIngredientShouldAddIngredientWithNullName() {
        Ingredient nullIngredient = new Ingredient(null, null, 0f);
        burger.addIngredient(nullIngredient);
        assertNull("Имя ингредиента должно быть null", burger.ingredients.get(0).getName());
    }

    @Test
    public void addIngredientShouldAddIngredientWithZeroPrice() {
        Ingredient nullIngredient = new Ingredient(null, null, 0f);
        burger.addIngredient(nullIngredient);
        assertEquals("Цена ингредиента должна быть 0", 0f, burger.ingredients.get(0).getPrice(), 0.001f);
    }
}

