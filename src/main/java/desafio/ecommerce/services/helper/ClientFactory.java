package desafio.ecommerce.services.helper;

import desafio.ecommerce.dtos.ClientDTO;
import desafio.ecommerce.dtos.PostClientDTO;
import desafio.ecommerce.models.ClientEntity;
import desafio.ecommerce.repositories.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class ClientFactory {

    private final ClientRepository clientRepository;

    public ClientFactory(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDTO registerClientFactory(PostClientDTO newClient) {
        ClientEntity newClientEntity = newClient.dtoToEntity();
        ClientEntity saveNewClient = clientRepository.save(newClientEntity);
        return ClientDTO.entityToDTO(saveNewClient);
    }
}
