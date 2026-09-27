package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.Bun;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты для класса Bun")
class BunTest {

    @Test
    @DisplayName("Конструктор должен сохранять переданное имя булочки")
    void constructorShouldSetName() {
        Bun bun = new Bun("Флюоресцентная булка", 988f);

        assertEquals("Флюоресцентная булка", bun.getName(),
                "Имя булочки должно совпадать с переданным в конструктор");
    }

    @Test
    @DisplayName("Конструктор должен сохранять переданную цену булочки")
    void constructorShouldSetPrice() {
        Bun bun = new Bun("Флюоресцентная булка", 988f);

        assertEquals(988f, bun.getPrice(), 0.0001f,
                "Цена булочки должна совпадать с переданной в конструктор");
    }

    @Test
    @DisplayName("getName() должен возвращать имя, установленное в конструкторе")
    void getNameShouldReturnConstructorValue() {
        Bun bun = new Bun("Краторная булка", 1255f);

        assertEquals("Краторная булка", bun.getName());
    }

    @Test
    @DisplayName("getPrice() должен возвращать цену, установленную в конструкторе")
    void getPriceShouldReturnConstructorValue() {
        Bun bun = new Bun("Краторная булка", 1255f);

        assertEquals(1255f, bun.getPrice(), 0.0001f);
    }

    @Test
    @DisplayName("Поля класса доступны напрямую (public)")
    void fieldsShouldBeAccessibleDirectly() {
        Bun bun = new Bun("Марсианская булка", 500f);

        assertEquals("Марсианская булка", bun.name);
        assertEquals(500f, bun.price, 0.0001f);
    }
}