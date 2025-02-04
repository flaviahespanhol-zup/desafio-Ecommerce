package desafio.ecommerce.exceptions;

public class ProductUnstockedException extends Exception {
    public ProductUnstockedException(String productName) {
        super(productName);
    }
}
