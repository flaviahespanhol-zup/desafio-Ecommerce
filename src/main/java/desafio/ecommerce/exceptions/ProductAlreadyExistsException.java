package desafio.ecommerce.exceptions;

public class ProductAlreadyExistsException extends AlreadyExistsException {
    public ProductAlreadyExistsException() {
        super("Produto com nome já existente!");
    }
}
