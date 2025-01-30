package desafio.ecommerce.dtos;

import desafio.ecommerce.models.ClientEntity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public record PostClientDTO(
        @NotEmpty(message = "O nome não pode estar vazio")
        @NotNull(message = "O nome não pode ser nulo")
        String name,
        @Column(unique = true)
        @NotEmpty(message = "O cpf não pode estar vazio")
        @NotNull(message = "O cpf não pode ser nulo")
        @CPF(message = "O cpf deve ser válido!")
        String cpf,
        @Column(unique = true)
        @NotEmpty(message = "O email não pode estar vazio")
        @NotNull(message = "O email não pode ser nulo")
        @Email(message = "O email deve ser um endereço de email válido!")
        String email) {

    public ClientEntity dtoToEntity() {
        ClientEntity newClient = new ClientEntity();
        newClient.setName(name);
        newClient.setCpf(cpf);
        newClient.setEmail(email);
        return newClient;
    }
}
