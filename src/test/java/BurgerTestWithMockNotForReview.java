import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static praktikum.IngredientType.FILLING;

@ExtendWith(MockitoExtension.class)
public class BurgerTestWithMockNotForReview {

	@Mock
	Bun bun;

	@Mock
	Ingredient ingredient;

	@Test
	public void checkBurgerWithMock() {

		Burger burger2 = new Burger();
		burger2.setBuns(bun);
		burger2.addIngredient(ingredient);

		Mockito.when(bun.getName()).thenReturn("black bun");
		Mockito.when(bun.getPrice()).thenReturn(100f);
		Mockito.when(ingredient.getType()).thenReturn(FILLING);
		Mockito.when(ingredient.getName()).thenReturn("dinosaur");
		Mockito.when(ingredient.getPrice()).thenReturn(200f);

		String expectedNameBun2 = "black bun";
		String actualNameBun2 = bun.getName();

		Float expectedPriceBun2 = 100f;
		Float actualPriceBun2 = bun.getPrice();

		assertEquals(expectedNameBun2, actualNameBun2);
		assertEquals(expectedPriceBun2, actualPriceBun2);


		String expectedTypeIngredient2 = String.valueOf(IngredientType.FILLING);
		String actualTypeIngredient2 = String.valueOf(ingredient.getType());

		String expectedNameIngredient2 = ingredient.getName();
		String actualNameIngredient2 = ingredient.getName();

		Float expectedPriceIngredient2 = 200f;
		Float actualPriceIngredient2 = ingredient.getPrice();

		assertEquals(expectedTypeIngredient2, actualTypeIngredient2);
		assertEquals(expectedNameIngredient2, actualNameIngredient2);
		assertEquals(expectedPriceIngredient2, actualPriceIngredient2);

		System.out.println("Цена получившегося бургера: " + burger2.getPrice());
		System.out.println("====================================================");
		System.out.println("Чек с информацией о бургере: " + burger2.getReceipt());
		System.out.println("====================================================");
		Mockito.when(ingredient.getName()).thenReturn("sausage");
		Mockito.when(ingredient.getPrice()).thenReturn(300f);
		burger2.moveIngredient(0, 0);
		System.out.println("Чек с информацией о бургере после смены начинки: " + burger2.getReceipt());
		System.out.println("====================================================");
		burger2.removeIngredient(0);
		System.out.println("Чек с информацией о бургере после удаления начинки: " + burger2.getReceipt());
	}
}
