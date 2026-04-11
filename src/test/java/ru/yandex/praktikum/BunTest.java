package ru.yandex.praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BunTest {

    @Test
    @DisplayName("Получение имени bun")
    public void getBunName() {

        Bun bun = new Bun("black bun", 100);

        assertEquals("black bun", bun.getName());
    }

    @Test
    @DisplayName("Получение цены bun")
    public void getBunPrice() {

        Bun bun = new Bun("white bun", 1000);

        assertEquals(1000, bun.getPrice(), 0.001);
    }

    @Test
    @DisplayName("Получение дробной цены bun")
    void getFloatPrice() {
        Bun bun = new Bun("red bun", 99.999f);

        assertEquals(99.999f, bun.getPrice(), 0.0001);
    }
}