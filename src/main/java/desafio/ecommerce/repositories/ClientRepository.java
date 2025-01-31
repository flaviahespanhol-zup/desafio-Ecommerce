package desafio.ecommerce.repositories;

import desafio.ecommerce.dtos.ClientDTO;
import desafio.ecommerce.models.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> getClientByCpf(String cpf);
}
