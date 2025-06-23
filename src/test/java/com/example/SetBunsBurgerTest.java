package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Database;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SetBunsBurgerTest {
    private static final Database database = new Database();
    private Burger burger;
    private final Bun bun;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    public SetBunsBurgerTest(Bun bun) {
        this.bun = bun;
    }

    @Parameterized.Parameters(name = "Тестовые данные:{0}")
    public static Object[][] data() {
        return database.availableBuns()
                .stream()
                .map(bun -> new Object[]{bun})
                .toArray(Object[][]::new);
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        assertEquals("Ошибка при задании булочки", bun, burger.bun);
    }
}
