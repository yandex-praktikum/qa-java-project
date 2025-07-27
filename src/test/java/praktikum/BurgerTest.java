package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();
    }

    @Test
    public void setBunsShouldSetBurgerBun() {
        burger.setBuns(bun);
        assertEquals("Булка должна быть установлена", bun, burger.bun);
    }

    @Test
    public void addIngredientShouldAddIngredientToList() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Соус фирменный", 80f);
        burger.addIngredient(ingredient);
        assertEquals("Список ингредиентов должен содержать 1 элемент", 1, burger.ingredients.size());
        assertEquals("Ингредиент должен быть добавлен", ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientShouldRemoveCorrectItem() {
        Ingredient ingredient1 = new Ingredient(IngredientType.SAUCE, "Соус фирменный", 80f);
        Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "Мясо бессмертных моллюсков Protostomia", 300f);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.removeIngredient(0);

        assertEquals("Список ингредиентов должен содержать 1 элемент", 1, burger.ingredients.size());
        assertEquals("Оставшийся ингредиент должен быть ingredient2", ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientShouldChangeOrderOfIngredients() {
        Ingredient ingredient1 = new Ingredient(IngredientType.SAUCE, "Соус фирменный", 80f);
        Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "Мясо бессмертных моллюсков Protostomia", 300f);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(1, 0);

        assertEquals("Первый элемент теперь должен быть ingredient2", ingredient2, burger.ingredients.get(0));
        assertEquals("Второй элемент теперь должен быть ingredient1", ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void getPriceShouldReturnCorrectTotal() {
        when(bun.getPrice()).thenReturn(125f);
        when(ingredient1.getPrice()).thenReturn(80f);
        when(ingredient2.getPrice()).thenReturn(300f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        float expectedPrice = 125f * 2 + 80f + 300f;
        assertEquals("Цена должна считаться правильно", expectedPrice, burger.getPrice(), 0.001f);
    }

    @Test
    public void getReceiptShouldReturnCorrectFormat() {
        when(bun.getName()).thenReturn("Флюоресцентная булка R2-D3");
        when(bun.getPrice()).thenReturn(125f);

        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient1.getName()).thenReturn("Соус фирменный");
        when(ingredient1.getPrice()).thenReturn(80f);

        when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient2.getName()).thenReturn("Мясо бессмертных моллюсков Protostomia");
        when(ingredient2.getPrice()).thenReturn(300f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        String expectedReceipt = String.format(
                "(==== %s ====)\n" +
                        "= %s %s =\n" +
                        "= %s %s =\n" +
                        "(==== %s ====)\n" +
                        "\nPrice: %.1f\n",
                bun.getName(),
                ingredient1.getType().toString().toLowerCase(),
                ingredient1.getName(),
                ingredient2.getType().toString().toLowerCase(),
                ingredient2.getName(),
                bun.getName(),
                125f*2 + 80f + 300f
        );

        assertEquals("Чек должен совпадать с ожидаемым", expectedReceipt, burger.getReceipt());
    }
}
