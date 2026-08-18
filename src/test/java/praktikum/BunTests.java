package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTests {

    @ParameterizedTest
    @MethodSource("getNameReturnValid")
    void getNameCorrect(String name, float price){
        Bun bun = new Bun(name, price);
        assertEquals(name,bun.getName(),"Expected: " + name + ", but actual: " + bun.getName());
    }

    private static Stream<Arguments> getNameReturnValid(){
        return Stream.of(
                Arguments.of("Лучшая булочка", 99.9f),
                Arguments.of("", 99.9f),
                Arguments.of("Почти пустая булочка!", 99.9f),
                Arguments.of("Целая булочка.", 99.9f),
                Arguments.of("negative булочка 123", 99.9f)
        );
    }

    @ParameterizedTest
    @MethodSource("getPriceReturnValid")
    void getPriceCorrect(String name, float price){
        Bun bun = new Bun(name, price);
        assertEquals(price,bun.getPrice(),"Expected: " + price + ", but actual: " + bun.getPrice());
    }

    private static Stream<Arguments> getPriceReturnValid(){
        return Stream.of(
                Arguments.of("Лучшая булочка!", 99.9f),
                Arguments.of("Лучшая булочка!", 0),
                Arguments.of("Лучшая булочка!", 0.001f),
                Arguments.of("Лучшая булочка!", 2),
                Arguments.of("Лучшая булочка!", -11)
        );
    }
}
