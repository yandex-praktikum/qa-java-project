import org.junit.jupiter.api.Test;
import praktikum.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static praktikum.IngredientType.FILLING;

public class BurgerTestNotForReview extends Burger {

	@Test
	public void checkBurgerWithoutMock() {

		Bun bun1 = new Bun("black bun", 100);
		Ingredient ingredient1 = new Ingredient(FILLING, "cutlet", 100);

		BurgerTestNotForReview burger1 = new BurgerTestNotForReview();
		burger1.setBuns(bun1);
		burger1.addIngredient(ingredient1);

		assertEquals("black bun", burger1.bun.getName());
		assertEquals(100, burger1.bun.getPrice());
		assertEquals(FILLING, burger1.ingredients.get(0).getType());
		assertEquals("cutlet", burger1.ingredients.get(0).getName());

		System.out.println(burger1.getReceipt());
	}
}
