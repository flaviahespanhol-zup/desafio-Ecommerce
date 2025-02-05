package desafio.ecommerce.exceptions;

public class ClientAlreadyExistsException extends AlreadyExistsException {
    public ClientAlreadyExistsException() {
        super("O cpf e/ou email já existem!");
    }
}
