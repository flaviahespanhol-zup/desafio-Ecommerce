package desafio.ecommerce.services;

import desafio.ecommerce.dtos.ClientDTO;
import desafio.ecommerce.dtos.PostClientDTO;
import desafio.ecommerce.exceptions.ClientNotFoundException;
import desafio.ecommerce.repositories.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDTO registerClientService(PostClientDTO newClient) {
        return clientRepository.registerClient(newClient);
    }

    public ClientDTO getClientByCpfService(String cpf) throws ClientNotFoundException {
        return clientRepository.getClientByCpf(cpf);
    }

    public ClientDTO updateClientFactoryService(Long id, PostClientDTO clientUpdated)
            throws ClientNotFoundException {
        return clientRepository.updateClient(id,clientUpdated);
    }
}
