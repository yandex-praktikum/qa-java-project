package praktikum.api.model;

import java.util.List;

public class OrderRequest {
    public List<String> ingredients;

    public OrderRequest(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
