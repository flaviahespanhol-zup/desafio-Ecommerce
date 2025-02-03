package desafio.ecommerce.services.helper;

import desafio.ecommerce.dtos.ClientDTO;
import desafio.ecommerce.dtos.PostClientDTO;
import desafio.ecommerce.exceptions.ClientNotFoundException;
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

    public ClientDTO getClientByCpfFactory(String cpf) throws ClientNotFoundException {
        ClientEntity clientByCpf = clientRepository.getClientByCpf(cpf)
                .orElseThrow(ClientNotFoundException::new);
        return new ClientDTO(clientByCpf.getId(), clientByCpf.getName(),
                clientByCpf.getCpf(), clientByCpf.getEmail());
    }

    private void updateClientFields(ClientEntity client, PostClientDTO clientUpdated) {
        client.setName(clientUpdated.name());
        client.setCpf(clientUpdated.cpf());
        client.setEmail(clientUpdated.email());
    }

    public ClientDTO updateClientFactory(Long id, PostClientDTO clientUpdated) throws ClientNotFoundException {
        ClientEntity client = clientRepository.findById(id)
                .orElseThrow(ClientNotFoundException::new);

        updateClientFields(client, clientUpdated);

        ClientEntity updatedClient = clientRepository.save(client);
        return ClientDTO.entityToDTO(updatedClient);
    }
}
