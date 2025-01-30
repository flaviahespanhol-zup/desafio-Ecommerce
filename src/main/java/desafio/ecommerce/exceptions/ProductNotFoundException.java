package desafio.ecommerce.exceptions;

public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException() {
        super("Produto não encontrado");
    }
}
