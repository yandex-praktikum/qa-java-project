import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

@ExtendWith(MockitoExtension.class)
public class BurgerTestMock {

    @Mock
    Burger burgerMock;

    @Test
    public void testBurgerMethodSetBuns(){
        Bun bun = new Bun("Очень вкусный бургер", 100.00F);
        burgerMock.setBuns(bun);
        Mockito.verify(burgerMock).setBuns(bun);
    }

    @Test
    public void testBurgerMethodAddIngredient(){
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Самый лучший бургер", 300.00F);
        burgerMock.addIngredient(ingredient);
        Mockito.verify(burgerMock).addIngredient(ingredient);
    }

}
