import praktikum.Bun;
import praktikum.Ingredient;
import praktikum.IngredientType;

public class BurgerContent {
    Bun whiteBun = new Bun("белая булка", 100F);
    Bun blackBun = new Bun("черная булка", 80F);
    Ingredient meat = new Ingredient(IngredientType.FILLING, "мясо", 200F);
    Ingredient salad = new Ingredient(IngredientType.FILLING,"салат",50F);
    Ingredient sauce = new Ingredient(IngredientType.SAUCE,"соус",30F);
}
