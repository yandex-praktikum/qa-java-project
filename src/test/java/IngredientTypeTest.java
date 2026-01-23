import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.IngredientType;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IngredientTypeTest {

	private static Stream<Arguments> ingredientTypeTestData() {
		return Stream.of(
				Arguments.of(IngredientType.SAUCE, "SAUCE", 2),
				Arguments.of(IngredientType.FILLING, "FILLING", 2)
		);
	}

	@ParameterizedTest
	@MethodSource("ingredientTypeTestData")
	public void checkIngredientType(IngredientType ingredientType, String expectedName, int expectedInt) {

		System.out.println(ingredientType);

		assertNotNull(ingredientType);
		assertEquals(expectedName, ingredientType.name());
		assertEquals(expectedInt, IngredientType.values().length);
	}
}
