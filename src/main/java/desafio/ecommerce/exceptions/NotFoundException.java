package desafio.ecommerce.exceptions;

public abstract class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }
}
