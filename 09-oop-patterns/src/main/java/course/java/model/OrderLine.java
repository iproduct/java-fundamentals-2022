package course.java.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine {
    private Book product;
    private int quantity;
    private int priceCents;

    public OrderLine(Book product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        priceCents = (int) (product.getPrice() * 100);
    }

    int getLineTotalCents(){
        return getQuantity() * getPriceCents();
    }


}
