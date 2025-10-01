package constants;

public class ExpectedReceipt {
    public static final String EXPECTED_RECEIPT_FOR_BLACK_BURGER =
            String.format("(==== %s ====)%n= sauce %s =%n(==== %s ====)%n%nPrice: %f%n",
                    "black bun","sour cream", "black bun", 40.0);
    public static final String EXPECTED_RECEIPT_FOR_WHITE_BURGER =
            String.format("(==== %s ====)%n= filling %s =%n(==== %s ====)%n%nPrice: %f%n",
                    "white bun", "cutlet", "white bun", 55.0);
}
