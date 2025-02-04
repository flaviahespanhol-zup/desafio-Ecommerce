package desafio.ecommerce.dtos;

import desafio.ecommerce.models.ClientEntity;

public record ClientDTO(Long id, String name, String cpf, String email) {

    public static ClientDTO entityToDTO (ClientEntity client) {
        return new ClientDTO(
                client.getId(), client.getName(), client.getCpf(),
                client.getEmail());
    }
}
