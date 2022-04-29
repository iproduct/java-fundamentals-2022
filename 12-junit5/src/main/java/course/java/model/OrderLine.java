package course.java.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderLine {
    @EqualsAndHashCode.Include
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
