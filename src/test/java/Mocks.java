import praktikum.Bun;
import praktikum.Ingredient;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Mocks {
    public static Bun getMockBun() {
        Bun mockBun = mock(Bun.class);
        when(mockBun.getPrice()).thenReturn(100F);
        when(mockBun.getName()).thenReturn("Булочка");
        return mockBun;
    }

    public static Ingredient getMockMeat() {
        Ingredient mockMeat = mock(Ingredient.class);
        when(mockMeat.getPrice()).thenReturn(200F);
        when(mockMeat.getName()).thenReturn("Мясо");
        when(mockMeat.getType()).thenReturn(praktikum.IngredientType.FILLING);
        return mockMeat;
    }

    public static Ingredient getMockSalad() {
        Ingredient mockSalad = mock(Ingredient.class);
        when(mockSalad.getPrice()).thenReturn(70F);
        when(mockSalad.getName()).thenReturn("Салат");
        when(mockSalad.getType()).thenReturn(praktikum.IngredientType.FILLING);
        return mockSalad;
    }

    public static Ingredient getMockSauce() {
        Ingredient mockSauce = mock(Ingredient.class);
        when(mockSauce.getPrice()).thenReturn(30F);
        when(mockSauce.getName()).thenReturn("Соус");
        when(mockSauce.getType()).thenReturn(praktikum.IngredientType.SAUCE);
        return mockSauce;
    }
}
