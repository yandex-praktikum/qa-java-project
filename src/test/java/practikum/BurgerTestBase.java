package practikum;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Ingredient;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.IngredientType;

import static org.mockito.Mockito.when;

public class BurgerTestBase {
    protected Burger burger;

    @Mock
    protected Bun bun;

    @Mock
    protected Ingredient ingredient1;

    @Mock
    protected Ingredient ingredient2;

    @Mock
    protected Ingredient ingredient3;

    protected void init() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
        initBunMock();
        initIngredientMocks();
    }

    protected void initBunMock() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
    }

    protected void initIngredientMocks() {
        initIngredientMock(ingredient1, IngredientType.SAUCE, "hot sauce", 100f);
        initIngredientMock(ingredient2, IngredientType.SAUCE, "sour cream", 200f);
        initIngredientMock(ingredient3, IngredientType.FILLING, "dinosaur", 300f);
    }

    protected void initIngredientMock(Ingredient ingredient, IngredientType type, String name, float price) {
        when(ingredient.getType()).thenReturn(type);
        when(ingredient.getName()).thenReturn(name);
        when(ingredient.getPrice()).thenReturn(price);
    }

    protected void addIngredient(Ingredient ingredient) {
        burger.addIngredient(ingredient);
    }
}
