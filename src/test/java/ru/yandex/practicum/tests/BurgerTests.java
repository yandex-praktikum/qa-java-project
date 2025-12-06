package ru.yandex.practicum.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;

import static org.junit.Assert.assertSame;


@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    @Mock
    private Bun bun;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBun_shouldSetValidBunBurgerTest() {

        burger.setBuns(bun);
        assertSame("Булочка должна быть установлена корректно", bun, burger.bun);
    }
}
