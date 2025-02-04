package desafio.ecommerce.repositories;

import desafio.ecommerce.dtos.ClientDTO;
import desafio.ecommerce.dtos.PostClientDTO;
import desafio.ecommerce.exceptions.ClientAlredyExistsException;
import desafio.ecommerce.exceptions.ClientNotFoundException;
import desafio.ecommerce.models.ClientEntity;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository {

    ClientRepositoryJPA clientRepositoryJPA;

    public ClientRepository(ClientRepositoryJPA clientRepositoryJPA) {
        this.clientRepositoryJPA = clientRepositoryJPA;
    }

    public ClientDTO registerClient(PostClientDTO newClient)
            throws ClientAlredyExistsException {
        boolean cpfExists = clientRepositoryJPA.getClientByCpf(newClient.cpf()).isPresent();
        boolean emailExists = clientRepositoryJPA.getClientByEmail(newClient.email()).isPresent();
        if (cpfExists || emailExists) {
            throw new ClientAlredyExistsException();
        }

        ClientEntity newClientEntity = newClient.dtoToEntity();
        ClientEntity saveNewClient = clientRepositoryJPA.save(newClientEntity);
        return ClientDTO.entityToDTO(saveNewClient);
    }

    public ClientDTO getClientByCpf(String cpf) throws ClientNotFoundException {
        ClientEntity clientByCpf = clientRepositoryJPA.getClientByCpf(cpf)
                .orElseThrow(ClientNotFoundException::new);
        return new ClientDTO(clientByCpf.getId(), clientByCpf.getName(),
                clientByCpf.getCpf(), clientByCpf.getEmail());
    }

    private void updateClientFields(ClientEntity client, PostClientDTO clientUpdated) {
        client.setName(clientUpdated.name());
        client.setCpf(clientUpdated.cpf());
        client.setEmail(clientUpdated.email());
    }

    public ClientDTO updateClient(Long id, PostClientDTO clientUpdated)
            throws ClientNotFoundException, ClientAlredyExistsException {
        ClientEntity client = clientRepositoryJPA.findById(id)
                .orElseThrow(ClientNotFoundException::new);

        boolean cpfExists = clientRepositoryJPA.getClientByCpf(clientUpdated.cpf()).isPresent();
        boolean emailExists = clientRepositoryJPA.getClientByEmail(clientUpdated.email()).isPresent();
        if (cpfExists || emailExists) {
            throw new ClientAlredyExistsException();
        }

        updateClientFields(client, clientUpdated);

        ClientEntity updatedClient = clientRepositoryJPA.save(client);
        return ClientDTO.entityToDTO(updatedClient);
    }
}
