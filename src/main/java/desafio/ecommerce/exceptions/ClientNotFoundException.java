package desafio.ecommerce.exceptions;

public class ClientNotFoundException extends NotFoundException {
    public ClientNotFoundException() {
        super("Cliente não encontrado");
    }
}
