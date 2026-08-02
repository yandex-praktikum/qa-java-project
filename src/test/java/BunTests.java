
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.Bun;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTests {

    @ParameterizedTest
    @MethodSource("getNameReturnValid")
    void getNameCorrect(String name, float price){
        Bun bun = new Bun(name, price);
        assertEquals(name,bun.getName(),"Expected: " + name + ", but actual: " + bun.getName());
        assertEquals(price,bun.getPrice(),"Expected: " + price + ", but actual: " + bun.getPrice());
    }

    private static Stream<Arguments> getNameReturnValid(){
        return Stream.of(
                Arguments.of("Лучшая булочка", 99.9f),
                Arguments.of("", 0),
                Arguments.of("Почти пустая булочка!", 0.001f),
                Arguments.of("Целая булочка", 2),
                Arguments.of("negative булочка 123", -11)
        );
    }
}
