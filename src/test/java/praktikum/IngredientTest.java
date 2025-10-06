package praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class IngredientTest {
    private Ingredient ingredient;
    private IngredientType expectedIngredientType = IngredientType.SAUCE;
    private String expectedName = "sauceName";
    private float expectedPrice = 50f;
    @Before
    public void prepareData() {
        this.ingredient = new Ingredient(
                expectedIngredientType,
                expectedName,
                expectedPrice
        );
    }
    @Test
    public void getPriceIsSuccess() {
        MatcherAssert.assertThat(
                "Возвращается некорретная стоимость ингредиента",
                ingredient.getPrice(),
                equalTo(expectedPrice)

        );

    }
    @Test
    public void getNameIsSuccess() {
        MatcherAssert.assertThat(
                "Возвращается некорректное наименование ингредиента",
                ingredient.getName(),
                equalTo(expectedName)
        );
    }
    @Test
    public void getTypeIsSuccess() {
        MatcherAssert.assertThat(
                "Возвращается некорректный тип ингредиента",
                ingredient.getType(),
                equalTo(expectedIngredientType)
        );
    }
}