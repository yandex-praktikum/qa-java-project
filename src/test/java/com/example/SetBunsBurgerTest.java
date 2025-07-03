package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class SetBunsBurgerTest {
    private Burger burger;

    @Parameterized.Parameter
    public Bun testBun;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Parameterized.Parameters(name = "Тест булочки: {0}")
    public static List<Bun> testBuns() {
        Bun whiteBun = mock(Bun.class);
        when(whiteBun.getName()).thenReturn("Белая булочка");

        Bun blackBun = mock(Bun.class);
        when(blackBun.getName()).thenReturn("Черная булочка");

        return Arrays.asList(whiteBun, blackBun);
    }

    @Test
    public void setBunsShouldNotReturnNull() {
        burger.setBuns(testBun);
        assertNotNull("Булочка должна быть установлена", burger.bun);
    }

    @Test
    public void setBunsShouldSetCorrectBun() {
        burger.setBuns(testBun);
        assertEquals("Должна быть установлена правильная булочка",
                testBun, burger.bun);
    }
}