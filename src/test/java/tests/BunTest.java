package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.Bun;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class BunTest {


    static Stream<Arguments> bunDataName() {
        return Stream.of(
                Arguments.of("Краторная булка N-200i"),
                Arguments.of("Флюоресцентная булка R2-D3")
        );
    }

    static Stream<Arguments> bunDataPrice() {
        return Stream.of(
                Arguments.of(1255.0f),
                Arguments.of(988.0f)
        );
    }

    @ParameterizedTest
    @MethodSource("bunDataName")
    @DisplayName("Проверка: Конструктор корректно сохраняет имя булочки")
    public void constructorShouldSetNameCorrectly(String name) {
        Bun bun = new Bun(name, 100.0f);
        assertEquals(name, bun.getName());
    }

    @ParameterizedTest
    @MethodSource("bunDataPrice")
    @DisplayName("Конструктор корректно сохраняет цену булочки")
    void constructorShouldSetPriceCorrectly(float price) {
        Bun bun = new Bun("Булка", price);
        assertEquals(price, bun.getPrice());
    }

    @Test
    @DisplayName("Проверка: getName() возвращает корректное имя булочки")
    void getNameShouldReturnCorrectValue() {
        Bun bun = new Bun("Флюоресцентная булка R2-D3", 988.0f);

        assertEquals("Флюоресцентная булка R2-D3", bun.getName(),
                "Метод getName() должен возвращать имя, переданное в конструктор");
    }

    @Test
    @DisplayName("Проверка: getPrice() возвращает корректную цену булочки")
    void getPriceShouldReturnCorrectValue(){
        Bun bun = new Bun("Краторная булка N-200i", 1255.0f);

        assertEquals(1255.0f, bun.getPrice(),
                "Метод getPrice() должен возвращать цену, переданную в конструктор" );

    }

}
