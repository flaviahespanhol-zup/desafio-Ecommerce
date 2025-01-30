package desafio.ecommerce.services;

import desafio.ecommerce.dtos.ClientDTO;
import desafio.ecommerce.dtos.PostClientDTO;
import desafio.ecommerce.services.helper.ClientFactory;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientFactory clientFactory;

    public ClientService(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    public ClientDTO registerClientService(PostClientDTO newClient) {
        return clientFactory.registerClientFactory(newClient);
    }
}
