package desafio.ecommerce.services.helper;

import desafio.ecommerce.repositories.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class ClientFactory {

    private final ClientRepository clientRepository;

    public ClientFactory(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
}
