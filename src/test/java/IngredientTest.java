import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

public class IngredientTest {

	private static Stream<Arguments> ingredientTestData() {
		return Stream.of(
				Arguments.of(FILLING, "cutlet", 100),
				Arguments.of(SAUCE, "chili sauce", 300)
		);
	}

	@ParameterizedTest
	@MethodSource("ingredientTestData")
	public void checkIngredients(IngredientType type, String name, float price) throws Exception {

		Ingredient ingredient = new Ingredient(type, name, price);

		String ingredientTestResult = "type: " + type + ", " + "name: " + name + ", " + "price: " + price + ".";
		System.out.println(ingredientTestResult);

		assertEquals(type, ingredient.getType(), "Ожидаемый и фактический Тип - совпадают: ");
		assertEquals(name, ingredient.getName(), "Ожидаемое и фактическое Имя - совпадают: ");
		assertEquals(price, ingredient.getPrice(), 0, "Ожидаемая и фактическая Цена - совпадают: ");
	}
}
