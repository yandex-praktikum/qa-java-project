import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@ExtendWith(MockitoExtension.class)
public class BurgerTestWithMock5toREVIEW {

	Burger burger;

	@Mock
	Bun bun0, bun1, bun2;

	@Mock
	Ingredient ingredient0, ingredient1, ingredient2, ingredient3, ingredient4, ingredient5;

	@Test
	public void setBurger() {

		burger = new Burger();
		burger.setBuns(bun0);
		burger.addIngredient(ingredient0);

		Mockito.when(bun0.getName()).thenReturn("black bun");
		Mockito.when(bun0.getPrice()).thenReturn(100f);
		Mockito.when(ingredient0.getType()).thenReturn(SAUCE);
		Mockito.when(ingredient0.getName()).thenReturn("hot sauce");
		Mockito.when(ingredient0.getPrice()).thenReturn(100f);

		String expectedNameBun1 = "black bun";
		String actualNameBun1 = bun0.getName();

		Float expectedPriceBun1 = 100f;
		Float actualPriceBun1 = bun0.getPrice();

		assertEquals(expectedNameBun1, actualNameBun1);
		assertEquals(expectedPriceBun1, actualPriceBun1);

		String expectedTypeIngredient1 = String.valueOf(IngredientType.SAUCE);
		String actualTypeIngredient1 = String.valueOf(ingredient0.getType());

		String expectedNameIngredient1 = "hot sauce";
		String actualNameIngredient1 = ingredient0.getName();

		Float expectedPriceIngredient1 = 100f;
		Float actualPriceIngredient1 = ingredient0.getPrice();

		assertEquals(expectedTypeIngredient1, actualTypeIngredient1);
		assertEquals(expectedNameIngredient1, actualNameIngredient1);
		assertEquals(expectedPriceIngredient1, actualPriceIngredient1);

		System.out.println("Цена получившегося бургера: " + burger.getPrice());
		System.out.println("====================================================");
		System.out.println("Чек с информацией о бургере: " + burger.getReceipt());
		System.out.println("====================================================");
	}

	@Test
	public void removeIngredientFromBurger() {

		burger = new Burger();
		burger.setBuns(bun1);
		burger.addIngredient(ingredient1);
		burger.addIngredient(ingredient3);

		Mockito.when(bun1.getName()).thenReturn("white bun");
		Mockito.when(bun1.getPrice()).thenReturn(200f);
		Mockito.when(ingredient1.getName()).thenReturn("sour cream");
		Mockito.when(ingredient3.getType()).thenReturn(FILLING);
		Mockito.when(ingredient3.getName()).thenReturn("cutlet");
		Mockito.when(ingredient3.getPrice()).thenReturn(100f);

		burger.removeIngredient(0);

		String expectedRemovedIngredient = "sour cream";
		String actualRemovedIngredient = ingredient1.getName();

		assertEquals(expectedRemovedIngredient, actualRemovedIngredient);

		System.out.println("Чек с информацией о бургере после смены начинки: " + burger.getReceipt());
	}

	@Test
	public void moveIngredientBurger() {

		burger = new Burger();
		burger.setBuns(bun2);
		burger.addIngredient(ingredient2);

		Mockito.when(bun2.getName()).thenReturn("red bun");
		Mockito.when(bun2.getPrice()).thenReturn(300f);
		Mockito.when(ingredient2.getType()).thenReturn(SAUCE);
		Mockito.when(ingredient2.getName()).thenReturn("chili sauce");
		Mockito.when(ingredient4.getName()).thenReturn("dinosaur");

		burger.moveIngredient(0, 0);

		String expectedRemovedIngredient = "dinosaur";
		String actualRemovedIngredient = ingredient4.getName();

		assertEquals(expectedRemovedIngredient, actualRemovedIngredient);

		System.out.println("Чек с информацией о бургере после удаления начинки: " + burger.getReceipt());
	}

	@Test
	public void getPriceBurger() {

		burger = new Burger();
		burger.setBuns(bun0);
		burger.addIngredient(ingredient5);

		Mockito.when(bun0.getPrice()).thenReturn(100f);
		Mockito.when(ingredient5.getPrice()).thenReturn(300f);

		Float expectedPriceBun = 100f;
		Float actualPriceBun = bun0.getPrice();

		Float expectedPriceIngredient = 300f;
		Float actualPriceIngredient = ingredient5.getPrice();

		assertEquals(expectedPriceBun, actualPriceBun);
		assertEquals(expectedPriceIngredient, actualPriceIngredient);

		System.out.println("Цена бургера: " + burger.getPrice());
	}

	@Test
	public void getReceiptBurger() {

		burger = new Burger();
		burger.setBuns(bun0);
		burger.setBuns(bun1);
		burger.setBuns(bun2);
		burger.addIngredient(ingredient0);
		burger.addIngredient(ingredient1);
		burger.addIngredient(ingredient2);
		burger.addIngredient(ingredient3);
		burger.addIngredient(ingredient4);
		burger.addIngredient(ingredient5);

		Mockito.when(bun0.getName()).thenReturn("black bun");
		Mockito.when(bun1.getName()).thenReturn("white bun");
		Mockito.when(bun2.getName()).thenReturn("red bun");
		Mockito.when(bun0.getPrice()).thenReturn(100f);
		Mockito.when(bun1.getPrice()).thenReturn(200f);
		Mockito.when(bun2.getPrice()).thenReturn(300f);
		Mockito.when(ingredient0.getType()).thenReturn(SAUCE);
		Mockito.when(ingredient1.getType()).thenReturn(SAUCE);
		Mockito.when(ingredient2.getType()).thenReturn(SAUCE);
		Mockito.when(ingredient3.getType()).thenReturn(FILLING);
		Mockito.when(ingredient4.getType()).thenReturn(FILLING);
		Mockito.when(ingredient5.getType()).thenReturn(FILLING);
		Mockito.when(ingredient0.getName()).thenReturn("hot sauce");
		Mockito.when(ingredient1.getName()).thenReturn("sour cream");
		Mockito.when(ingredient2.getName()).thenReturn("chili sauce");
		Mockito.when(ingredient3.getName()).thenReturn("cutlet");
		Mockito.when(ingredient4.getName()).thenReturn("dinosaur");
		Mockito.when(ingredient5.getName()).thenReturn("sausage");
		Mockito.when(ingredient0.getPrice()).thenReturn(100f);
		Mockito.when(ingredient1.getPrice()).thenReturn(200f);
		Mockito.when(ingredient2.getPrice()).thenReturn(300f);
		Mockito.when(ingredient3.getPrice()).thenReturn(100f);
		Mockito.when(ingredient4.getPrice()).thenReturn(200f);
		Mockito.when(ingredient5.getPrice()).thenReturn(300f);

		String expectedNameBun0 = "black bun";
		String actualNameBun0 = bun0.getName();
		String expectedNameBun1 = "white bun";
		String actualNameBun1 = bun1.getName();
		String expectedNameBun2 = "red bun";
		String actualNameBun2 = bun2.getName();
		assertEquals(expectedNameBun0, actualNameBun0);
		assertEquals(expectedNameBun1, actualNameBun1);
		assertEquals(expectedNameBun2, actualNameBun2);

		Float expectedPriceBun0 = 100f;
		Float actualPriceBun0 = bun0.getPrice();
		Float expectedPriceBun1 = 200f;
		Float actualPriceBun1 = bun1.getPrice();
		Float expectedPriceBun2 = 300f;
		Float actualPriceBun2 = bun2.getPrice();
		assertEquals(expectedPriceBun0, actualPriceBun0);
		assertEquals(expectedPriceBun1, actualPriceBun1);
		assertEquals(expectedPriceBun2, actualPriceBun2);

		String expectedNameIngredient0 = "hot sauce";
		String actualNameIngredient0 = ingredient0.getName();
		String expectedNameIngredient1 = "sour cream";
		String actualNameIngredient1 = ingredient1.getName();
		String expectedNameIngredient2 = "chili sauce";
		String actualNameIngredient2 = ingredient2.getName();
		String expectedNameIngredient3 = "cutlet";
		String actualNameIngredient3 = ingredient3.getName();
		String expectedNameIngredient4 = "dinosaur";
		String actualNameIngredient4 = ingredient4.getName();
		String expectedNameIngredient5 = "sausage";
		String actualNameIngredient5 = ingredient5.getName();
		assertEquals(expectedNameIngredient0, actualNameIngredient0);
		assertEquals(expectedNameIngredient1, actualNameIngredient1);
		assertEquals(expectedNameIngredient2, actualNameIngredient2);
		assertEquals(expectedNameIngredient3, actualNameIngredient3);
		assertEquals(expectedNameIngredient4, actualNameIngredient4);
		assertEquals(expectedNameIngredient5, actualNameIngredient5);

		Float expectedPriceIngredient0 = 100f;
		Float actualPriceIngredient0 = ingredient0.getPrice();
		Float expectedPriceIngredient1 = 200f;
		Float actualPriceIngredient1 = ingredient1.getPrice();
		Float expectedPriceIngredient2 = 300f;
		Float actualPriceIngredient2 = ingredient2.getPrice();
		Float expectedPriceIngredient3 = 100f;
		Float actualPriceIngredient3 = ingredient3.getPrice();
		Float expectedPriceIngredient4 = 200f;
		Float actualPriceIngredient4 = ingredient4.getPrice();
		Float expectedPriceIngredient5 = 300f;
		Float actualPriceIngredient5 = ingredient5.getPrice();
		assertEquals(expectedPriceIngredient0, actualPriceIngredient0);
		assertEquals(expectedPriceIngredient1, actualPriceIngredient1);
		assertEquals(expectedPriceIngredient2, actualPriceIngredient2);
		assertEquals(expectedPriceIngredient3, actualPriceIngredient3);
		assertEquals(expectedPriceIngredient4, actualPriceIngredient4);
		assertEquals(expectedPriceIngredient5, actualPriceIngredient5);

		System.out.println("Чек за бургер: " + burger.getReceipt());
	}
}
