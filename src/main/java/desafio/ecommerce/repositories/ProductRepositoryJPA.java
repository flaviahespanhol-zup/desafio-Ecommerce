package desafio.ecommerce.repositories;

import desafio.ecommerce.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepositoryJPA extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByName(String productName);
}
