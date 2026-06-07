package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
//import org.mockito.Mockito;
import org.assertj.core.api.SoftAssertions;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import static org.mockito.Mockito.*;

// Перемещение с моками

@RunWith(Parameterized.class)
public class BurgerMoveParameterizedTest {

    private Burger burger;
    private final List<String> initialNames;
    private final int from;
    private final int to;
    private final List<String> expectedNames;

    public BurgerMoveParameterizedTest(List<String> initialNames, int from, int to, List<String> expectedNames) {
        this.initialNames = initialNames;
        this.from = from;
        this.to = to;
        this.expectedNames = expectedNames;
    }

    @Before
    public void setUp() {

        Map<String, Ingredient> ingredientMocks = new HashMap<>();

        Bun bunMock = mock(Bun.class);
        when(bunMock.getPrice()).thenReturn(100f);

        // Моки ингредиентов
        for (String name : initialNames) {
            if (!ingredientMocks.containsKey(name)) {
                Ingredient mock = mock(Ingredient.class);
                when(mock.getName()).thenReturn(name);
                when(mock.getPrice()).thenReturn(50f);
                when(mock.getType()).thenReturn(IngredientType.FILLING);
                ingredientMocks.put(name, mock);
            }
        }

        //Моки имён
        for (String name : expectedNames) {
            if (!ingredientMocks.containsKey(name)) {
                Ingredient mock = mock(Ingredient.class);
                when(mock.getName()).thenReturn(name);
                when(mock.getPrice()).thenReturn(50f);
                when(mock.getType()).thenReturn(IngredientType.FILLING);
                ingredientMocks.put(name, mock);
            }
        }

        //Моки - ингредиенты
        burger = new Burger();
        burger.setBuns(bunMock);
        for (String name : initialNames) {
            burger.addIngredient(ingredientMocks.get(name));
        }
    }

    @Parameterized.Parameters(name = "move from {1} to {2} -> {3}")
    public static Iterable<Object[]> scenarios() {
        return Arrays.asList(new Object[][]{
                {Arrays.asList("A", "B", "C"), 0, 2, Arrays.asList("B", "C", "A")},
                {Arrays.asList("A", "B", "C"), 2, 0, Arrays.asList("C", "A", "B")},
                {Arrays.asList("A", "B", "C"), 1, 2, Arrays.asList("A", "C", "B")},
                {Arrays.asList("A", "B", "C"), 0, 1, Arrays.asList("B", "A", "C")},
                // Перемещение элемента на свою же позицию (список не меняется)
                {Arrays.asList("A", "B", "C"), 1, 1, Arrays.asList("A", "B", "C")},
                // 2 элемента
                {Arrays.asList("A", "B"), 0, 1, Arrays.asList("B", "A")},
                {Arrays.asList("A", "B"), 1, 0, Arrays.asList("B", "A")},
        });
    }

    @Test
    public void shouldMoveIngredientCorrectly() {
        //Перемещение
        burger.moveIngredient(from, to);

        //Проверка
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(burger.ingredients)
                    .as("Размер списка не должен измениться после перемещения")
                    .hasSize(initialNames.size());

            //Проверяем порядок
            for (int i = 0; i < expectedNames.size(); i++) {
                softly.assertThat(burger.ingredients.get(i).getName())
                        .as("Ингредиент на позиции " + i + " должен совпадать с ожидаемым")
                        .isEqualTo(expectedNames.get(i));
            }
        });
    }
}