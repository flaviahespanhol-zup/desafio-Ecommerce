package desafio.ecommerce.dtos;

import desafio.ecommerce.models.ClientEntity;

public record PostClientDTO(String name, String cpf, String email) {

    public ClientEntity dtoToEntity() {
        ClientEntity newClient = new ClientEntity();
        newClient.setName(name);
        newClient.setCpf(cpf);
        newClient.setEmail(email);
        return newClient;
    }
}
