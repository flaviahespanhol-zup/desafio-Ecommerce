package desafio.ecommerce.repositories;

import desafio.ecommerce.models.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepositoryJPA extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> getClientByCpf(String cpf);
    Optional<ClientEntity> getClientByEmail(String email);
}
