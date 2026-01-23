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
public class BurgerTestWithMock4NotForReview {

	@Mock
	Bun bun;

	@Mock
	Ingredient ingredient;

	public Burger createBurger() {
		Burger burger = new Burger();
		burger.setBuns(bun);
		burger.addIngredient(ingredient);
		return burger;
	}

	@Test
	public void setBurger() {
		Burger burger2 = createBurger();

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

		String expectedNameIngredient2 = "dinosaur";
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

	}

	@Test
	public void removeIngredientFromBurger() {
		Burger burger3 = createBurger();;

		Mockito.when(bun.getName()).thenReturn("white bun");
		Mockito.when(bun.getPrice()).thenReturn(200f);
		Mockito.when(ingredient.getName()).thenReturn("sausage");

		burger3.ingredients.remove(0);

		String expectedRemovedIngredient = "sausage";
		String actualRemovedIngredient = ingredient.getName();

		assertEquals(expectedRemovedIngredient, actualRemovedIngredient);

		System.out.println("Чек с информацией о бургере после смены начинки: " + burger3.getReceipt());
	}

	@Test
	public void moveIngredientBurger() {
		Burger burger4 = createBurger();

		Mockito.when(bun.getName()).thenReturn("black bun");
		Mockito.when(bun.getPrice()).thenReturn(100f);
		Mockito.when(ingredient.getType()).thenReturn(FILLING);
		Mockito.when(ingredient.getName()).thenReturn("sausage");
		Mockito.when(ingredient.getName()).thenReturn("dinosaur");

		burger4.ingredients.add(0, burger4.ingredients.remove(0));

		String expectedRemovedIngredient = "dinosaur";
		String actualRemovedIngredient = ingredient.getName();

		assertEquals(expectedRemovedIngredient, actualRemovedIngredient);

		System.out.println("Чек с информацией о бургере после удаления начинки: " + burger4.getReceipt());
	}

	@Test
	public void getPriceBurger() {
		Burger burger5 = createBurger();

		Mockito.when(bun.getPrice()).thenReturn(100f);

		Float expectedPriceBun4 = 100f;
		Float actualPriceBun4 = bun.getPrice();

		assertEquals(expectedPriceBun4, actualPriceBun4);

		System.out.println("Цена бургера: " + burger5.getPrice());

	}

	@Test
	public void getReceiptBurger() {
		Burger burger6 = createBurger();

		Mockito.when(bun.getName()).thenReturn("white bun");
		Mockito.when(bun.getPrice()).thenReturn(200f);
		Mockito.when(ingredient.getType()).thenReturn(FILLING);
		Mockito.when(ingredient.getName()).thenReturn("sausage");
		Mockito.when(ingredient.getPrice()).thenReturn(300f);

		Float expectedPriceBun6 = 200f;
		Float actualPriceBun6 = bun.getPrice();

		String expectedNameIngredient6 = "sausage";
		String actualNameIngredient6 = ingredient.getName();

		Float expectedPriceIngredient6 = 300f;
		Float actualPriceIngredient6 = ingredient.getPrice();

		assertEquals(expectedPriceBun6, actualPriceBun6);
		assertEquals(expectedNameIngredient6, actualNameIngredient6);
		assertEquals(expectedPriceIngredient6, actualPriceIngredient6);

		System.out.println("Чек за бургер: " + burger6.getReceipt());

	}
}
