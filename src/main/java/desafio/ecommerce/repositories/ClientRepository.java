package desafio.ecommerce.repositories;

import desafio.ecommerce.dtos.ClientDTO;
import desafio.ecommerce.models.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    ClientEntity getClientByCpf(String cpf);
}
