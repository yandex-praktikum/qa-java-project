package BurgerTests;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.*;

import static dataTest.dataBurger.*;
import static org.mockito.Mockito.*;

public class BurgerBaseTest {

    @Mock
    protected Bun mockBun;

    @Mock
    protected  Ingredient mockIngredient1;

    @Mock
    protected  Ingredient mockIngredient2;

    @InjectMocks
    protected  Burger burger;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(mockBun.getPrice()).thenReturn(50.0f);
        when(mockBun.getName()).thenReturn(BUN_NAME);

        when(mockIngredient1.getPrice()).thenReturn(30.0f);
        when(mockIngredient1.getName()).thenReturn(INGREDIENT_NAME_1);
        when(mockIngredient1.getType()).thenReturn(IngredientType.FILLING);

        when(mockIngredient2.getPrice()).thenReturn(10.0f);
        when(mockIngredient2.getName()).thenReturn(INGREDIENT_NAME_2);
        when(mockIngredient2.getType()).thenReturn(IngredientType.SAUCE);


    }
}