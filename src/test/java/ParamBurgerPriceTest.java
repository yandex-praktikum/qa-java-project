import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class ParamBurgerPriceTest {
    private Bun mockBun;
    private Ingredient[] mockIngredients;
    private float expectedPrice;

    public ParamBurgerPriceTest(float bunPrice, Ingredient[] ingredients, float expectedPrice) {
//    мок для булочки
        this.mockBun = mock(Bun.class);
        when(mockBun.getPrice()).thenReturn(bunPrice);

//    мок для ингредиентов
        this.mockIngredients = new Ingredient[ingredients.length];
        for (int i = 0; i < ingredients.length; i++) {
            Ingredient mockIngredient = mock(Ingredient.class);
            when(mockIngredient.getPrice()).thenReturn(ingredients[i].getPrice());
            this.mockIngredients[i] = mockIngredient;
        }
        this.expectedPrice = expectedPrice;
    }

        @Parameterized.Parameters
        public static Collection<Object[]> getTestData () {
            return Arrays.asList(new Object[][]{
//                    только булочка
                    {50.0f, new Ingredient[0], 100.0f},
//                    булочка и  начинка
                    {40.0f, new Ingredient[]{new Ingredient(IngredientType.FILLING, "cutlet", 100.0f)}, 180.0f},
//                    булочка и соусы
                    {30.0f, new Ingredient[]{
                            new Ingredient(IngredientType.SAUCE, "hot sauce", 100.0f),
                            new Ingredient(IngredientType.SAUCE, "sour cream", 200.0f)}, 360.0f},
//                    булочка, начинки и соус
                    {20.0f, new Ingredient[]{
                            new Ingredient(IngredientType.FILLING, "cutlet", 100.0f),
                            new Ingredient(IngredientType.FILLING, "dinosaur", 200.0f),
                            new Ingredient(IngredientType.SAUCE, "hot sauce", 100.0f)}, 440.0f}
            });
        }

        @Test
        public void shouldCalculateCorrectPrice () {
            Burger burger = new Burger();
            burger.setBuns(mockBun);
            for (Ingredient ingredient : mockIngredients) {
                burger.addIngredient(ingredient);
            }

            float actualPrice = burger.getPrice();
            System.out.println("ожидаемая цена: " + expectedPrice);
            System.out.println("факт  цена: " + actualPrice);
            assertEquals(expectedPrice, actualPrice, 0.001f);
        }
    }