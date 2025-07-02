package praktikum.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class MoveIngredientParameterizedTest {
    private Burger burger;
    private int index;
    private int newIndex;

    @Mock
    Ingredient firstIngredient;

    @Mock
    Ingredient secondIngredient;

    @Mock
    Ingredient thirdIngredient;

    public MoveIngredientParameterizedTest(int index, int newIndex) {
        this.index = index;
        this.newIndex = newIndex;
    }


    @Before
    public void initBurger() {
        MockitoAnnotations.initMocks(this);

        // Мок для первого ингредиента
        when(firstIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(firstIngredient.getName()).thenReturn("chili sauce");
        when(firstIngredient.getPrice()).thenReturn(300F);

        // Мок для второго ингредиента
        when(secondIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(secondIngredient.getName()).thenReturn("cutlet");
        when(secondIngredient.getPrice()).thenReturn(100F);

        // Мок для третьего ингредиента
        when(thirdIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(thirdIngredient.getName()).thenReturn("dinosaur");
        when(thirdIngredient.getPrice()).thenReturn(200F);

        burger = new Burger();

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);
    }

    @Parameterized.Parameters(name = "Тест №{index}: индекс в списке был изменен с {0} на {1}")
    public static Object[] getIngredientsIndex() {
        return new Object[][]{
            {0, 2},
            {1, 1},
            {2, 0}
        };
    }

    // Тестирование метода moveIngredient в классе Burger
    // Индекс ингредиента изменен
    @Test
    public void ingredientIndexChanged() {
        List<Ingredient> originalIngredients = new ArrayList<>(burger.ingredients);
        Ingredient ingredientToMove = originalIngredients.get(index);

        // Размер списка до перемещения ингредиентов
        int originalSize = originalIngredients.size();

        // Вызываем метод moveIngredient, изменяем индекс элемента
        burger.moveIngredient(index, newIndex);

        // Проверяем, что индекс элемента изменен
        assertEquals(ingredientToMove, burger.ingredients.get(newIndex));
    }

    // Тестирование метода moveIngredient в классе Burger
    // Размер списка остался без изменений
    @Test
    public void theListSizeRemainsUnchanged() {
        List<Ingredient> originalIngredients = new ArrayList<>(burger.ingredients);
        Ingredient ingredientToMove = originalIngredients.get(index);

        // Размер списка до перемещения ингредиентов
        int originalSize = originalIngredients.size();

        // Вызываем метод moveIngredient, изменяем индекс элемента
        burger.moveIngredient(index, newIndex);

        // Проверяем, что размер списка остался без изменений
        assertEquals(originalSize, burger.ingredients.size());
    }

}