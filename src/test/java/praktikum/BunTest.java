package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {
    @ParameterizedTest
    @MethodSource("dataProvider")
    void bunParametersCorrect(String name, float price){
        Bun bun = new Bun(name, price);
        assertAll(
                () -> assertEquals(name, bun.getName()),
                () -> assertEquals(price, bun.getPrice())
        );
    }

    private static Stream<Arguments> dataProvider() {
        Database data = new Database();
        return Stream.of(
                Arguments.of(data.availableBuns().get(0).getName(), data.availableBuns().get(0).getPrice()),
                Arguments.of(data.availableBuns().get(1).getName(), data.availableBuns().get(1).getPrice())
        );
    }
}
