import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.Bun;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BunTest {

	private static Stream<Arguments> bunTestData() {
		return Stream.of(
				Arguments.of("black bun", 100),
				Arguments.of("white bun", 200),
				Arguments.of(null, 200),
				Arguments.of("black bun black bun black bun black bun black bun black bun black bun black bun black bun black bun black bun black bun", 200),
				Arguments.of("black@bun#_", 200),
				Arguments.of("white bun", -200),
				Arguments.of("white bun", 0),
				Arguments.of("white bun", 100.0f),
				Arguments.of("white bun", 300.0f)
		);
	}

	@ParameterizedTest(name = "Проверка булочки: name={0}, price={1}")
	@MethodSource("bunTestData")
	public void checkBun(String name, float price) throws Exception {

		Bun bun = new Bun(name, price);

		String resultToString = "name= " + name + ", price= " + price + ".";
		System.out.println(resultToString);

		assertEquals(name, bun.getName(), "Ожидаемое и фактическое Имя - совпадают: ");
		assertEquals(price, bun.getPrice(), 0, "Ожидаемая и фактическая Цена - совпадают: ");

	}
}
