import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Database;
import praktikum.Ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class BurgerTestNew3 {

	Database db = new Database();

	Burger burger;

	@Mock
	Bun bun0, bun1, bun2;

	@Mock
	Ingredient ingredient0, ingredient1, ingredient2, ingredient3, ingredient4, ingredient5;

	@Test
	public void setBurger() {

		burger = new Burger();
		burger.setBuns(db.availableBuns().get(0));
		burger.addIngredient(db.availableIngredients().get(0));

		Mockito.when(bun0.getName()).thenReturn("black bun");
		Mockito.when(ingredient0.getName()).thenReturn("hot sauce");

		assertEquals("black bun", burger.bun.getName());
		assertEquals(100f, burger.bun.getPrice());
		assertEquals("hot sauce", burger.ingredients.get(0).getName());
		assertEquals(300f, burger.getPrice());
		assertTrue(burger.getReceipt().contains("black bun"));
		assertTrue(burger.getReceipt().contains("hot sauce"));

		assertEquals("black bun", bun0.getName());
		assertEquals("hot sauce", ingredient0.getName());

		System.out.println("Цена получившегося бургера: " + burger.getPrice());
		System.out.println("====================================================");
		System.out.println("Чек с информацией о бургере: " + burger.getReceipt());
		System.out.println("====================================================");
	}

	@Test
	public void removeIngredientFromBurger() {

		burger = new Burger();
		burger.setBuns(db.availableBuns().get(1));
		burger.addIngredient(db.availableIngredients().get(1));
		burger.addIngredient(db.availableIngredients().get(3));

		Mockito.when(bun1.getName()).thenReturn("white bun");
		Mockito.when(ingredient1.getName()).thenReturn("sour cream");

		String actualRemovedIngredient = burger.ingredients.get(0).getName();
		burger.removeIngredient(0);

		assertEquals("sour cream", actualRemovedIngredient);
		assertEquals("cutlet",  burger.ingredients.get(0).getName());
		assertEquals(500f, burger.getPrice());
		assertTrue(burger.getReceipt().contains("white bun"));
		assertTrue(burger.getReceipt().contains("cutlet"));

		assertEquals("white bun", bun1.getName());
		assertEquals("sour cream", ingredient1.getName());

		System.out.println("Цена получившегося бургера после смены начинки: " + burger.getPrice());
		System.out.println("====================================================");
		System.out.println("Чек с информацией о бургере после смены начинки: " + burger.getReceipt());
		System.out.println("====================================================");
	}

	@Test
	public void moveIngredientBurger() {

		burger = new Burger();
		burger.setBuns(db.availableBuns().get(2));
		burger.addIngredient(db.availableIngredients().get(2));
		burger.addIngredient(db.availableIngredients().get(4));

		Mockito.when(bun2.getName()).thenReturn("red bun");
		Mockito.when(ingredient2.getName()).thenReturn("dinosaur");
		Mockito.when(ingredient4.getName()).thenReturn("chili sauce");

		burger.moveIngredient(0, 1);

		String expectedMovedIngredient = "chili sauce";
		String actualMovedIngredient = burger.ingredients.get(1).getName();
		assertEquals(expectedMovedIngredient, actualMovedIngredient);
		assertEquals(1100f, burger.getPrice());
		assertTrue(burger.getReceipt().contains("red bun"));
		assertTrue(burger.getReceipt().contains("dinosaur"));
		assertTrue(burger.getReceipt().contains("chili sauce"));

		assertEquals("red bun", bun2.getName());
		assertEquals("dinosaur", ingredient2.getName());
		assertEquals("chili sauce", ingredient4.getName());

		System.out.println("Чек с информацией о бургере после удаления начинки: " + burger.getPrice());
		System.out.println("Чек с информацией о бургере после удаления начинки: " + burger.getReceipt());
	}

	@Test
	public void getPriceBurger() {

		burger = new Burger();
		burger.setBuns(db.availableBuns().get(0));
		burger.addIngredient(db.availableIngredients().get(2));

		Mockito.when(bun0.getName()).thenReturn("black bun");
		Mockito.when(bun0.getPrice()).thenReturn(100f);
		Mockito.when(ingredient2.getName()).thenReturn("chili sauce");
		Mockito.when(ingredient2.getPrice()).thenReturn(300f);

		Float actualPriceIngredient = burger.ingredients.get(0).getPrice();

		assertEquals(100f * 2, burger.bun.getPrice() * 2);
		assertEquals(300f, actualPriceIngredient);
		assertTrue(burger.getReceipt().contains("500,00"));

		assertEquals("black bun", bun0.getName());
		assertEquals(100f, bun0.getPrice());
		assertEquals("chili sauce", ingredient2.getName());
		assertEquals(300f, ingredient2.getPrice());

		System.out.println("Цена бургера: " + burger.getPrice());
		System.out.println("Цена бургера: " + burger.getReceipt());
	}

	@Test
	public void getReceiptBurger() {

		burger = new Burger();
		burger.setBuns(db.availableBuns().get(0));
		burger.addIngredient(db.availableIngredients().get(0));
		burger.addIngredient(db.availableIngredients().get(3));

		Mockito.when(bun0.getName()).thenReturn("black bun");
		Mockito.when(bun0.getPrice()).thenReturn(100f);
		Mockito.when(ingredient0.getName()).thenReturn("hot sauce");
		Mockito.when(ingredient0.getPrice()).thenReturn(100f);
		Mockito.when(ingredient3.getName()).thenReturn("cutlet");
		Mockito.when(ingredient3.getPrice()).thenReturn(100f);

		assertEquals("black bun", burger.bun.getName());
		assertEquals(100f, burger.bun.getPrice());
		assertEquals("hot sauce", burger.ingredients.get(0).getName());
		assertEquals(100f, burger.ingredients.get(0).getPrice());
		assertEquals(400f, burger.getPrice());

		assertEquals("black bun", bun0.getName());
		assertEquals(100f, bun0.getPrice());
		assertEquals("hot sauce", ingredient0.getName());
		assertEquals(100f, ingredient0.getPrice());
		assertEquals("cutlet", ingredient3.getName());
		assertEquals(100f, ingredient3.getPrice());


		System.out.println(burger.getReceipt());
		String expectedNamesIngredientAndExpectedPriceIngredients = "(==== black bun ====)\n" +
				"= sauce hot sauce =\n" +
				"= filling cutlet =\n" +
				"(==== black bun ====)\n" +
				"\n" +
				"Price: 400,000000\n";

		String actualNamesIngredientAndExpectedPriceIngredients = burger.getReceipt();
		assertEquals(expectedNamesIngredientAndExpectedPriceIngredients.replace("\r\n", "\n"), actualNamesIngredientAndExpectedPriceIngredients.replace("\r\n", "\n"));
	}
}
