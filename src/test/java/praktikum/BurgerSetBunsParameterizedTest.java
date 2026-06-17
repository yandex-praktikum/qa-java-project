package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Assertions;

import java.util.stream.Stream;

public class BurgerSetBunsParameterizedTest {

    static Stream<Arguments> getBunData() {
        return Stream.of(
                Arguments.of("black bun", 100f),
                Arguments.of("white bun", 200f),
                Arguments.of("red bun", 300f),
                Arguments.of("special bun", 500f)
        );
    }

    @ParameterizedTest
    @MethodSource("getBunData")
    @DisplayName("setBuns: установка разных булочек")
    public void testSetBunsWithDifferentParameters(String bunName, float bunPrice) {
        Burger burger = new Burger();
        Bun bun = new Bun(bunName, bunPrice);

        burger.setBuns(bun);

        Assertions.assertAll(
                () -> Assertions.assertEquals(bunName, burger.bun.getName()),
                () -> Assertions.assertEquals(bunPrice, burger.bun.getPrice(), 0.001)
        );
    }
}