package desafio.ecommerce.exceptions;

public class ClientAlredyExistsException extends AlreadyExistsException {
    public ClientAlredyExistsException() {
        super("O cpf e/ou email já existem!");
    }
}
