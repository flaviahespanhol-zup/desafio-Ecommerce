package desafio.ecommerce.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import desafio.ecommerce.dtos.PostPurchaseDTO;
import desafio.ecommerce.exceptions.ClientNotFoundException;
import desafio.ecommerce.exceptions.ProductNotFoundException;
import desafio.ecommerce.exceptions.ProductUnstockedException;
import desafio.ecommerce.models.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PurchaseRepository {
    private final ClientRepositoryJPA clientRepository;
    private final ProductRepositoryJPA productRepository;

    public PurchaseRepository(ClientRepositoryJPA clientRepository, ProductRepositoryJPA productRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    public List<String> shopping(PostPurchaseDTO purchase)
            throws ClientNotFoundException, ProductNotFoundException, ProductUnstockedException {

        validateClient(purchase.cpf());

        List<String> productsNotAvailable = new ArrayList<>();
        List<ProductEntity> productsAvailable = new ArrayList<>();

        processProducts(purchase, productsNotAvailable, productsAvailable);

        if (!productsNotAvailable.isEmpty()) {
            throw new ProductNotFoundException();
        }

        updateProductStock(productsAvailable);

        return extractProductNames(productsAvailable);
    }

    private void validateClient(String cpf) throws ClientNotFoundException {
        clientRepository.getClientByCpf(cpf)
                .orElseThrow(ClientNotFoundException::new);
    }

    private void processProducts(PostPurchaseDTO purchase, List<String> productsNotAvailable,
                                 List<ProductEntity> productsAvailable)
            throws ProductNotFoundException, ProductUnstockedException {
        for (PostPurchaseDTO.PurchaseProductDTO productDTO : purchase.products()) {
            ProductEntity product = findProductByName(productDTO.name());
            checkStock(product, productsNotAvailable, productsAvailable);
        }

        if (!productsNotAvailable.isEmpty()) {
            throw new ProductUnstockedException(generateOutOfStockErrorMessage(productsNotAvailable));
        }
    }

    private void checkStock(ProductEntity product, List<String> productsNotAvailable,
                            List<ProductEntity> productsAvailable) {
        if (isProductOutOfStock(product)) {
            productsNotAvailable.add(product.getName());
        } else {
            productsAvailable.add(product);
        }
    }

    private String generateOutOfStockErrorMessage(List<String> productsNotAvailable) {
        StringBuilder productsUnstocked = new StringBuilder();
        for (int i = 0; i < productsNotAvailable.size(); i++) {
            productsUnstocked.append(productsNotAvailable.get(i));
            if (i < productsNotAvailable.size() - 1) {
                productsUnstocked.append(", ");
            }
        }
        Map<String, String> response = Map.of("erro", "Produto(s) em falta: " + productsUnstocked);
        try {
            return new ObjectMapper().writeValueAsString(response);
        } catch (JsonProcessingException e) {
            return "{\"erro\": \"Erro ao processar a mensagem de produtos em falta.\"}";
        }
    }

    private ProductEntity findProductByName(String productName) throws ProductNotFoundException {
        return productRepository.findByName(productName)
                .orElseThrow(ProductNotFoundException::new);
    }

    private boolean isProductOutOfStock(ProductEntity product) {
        return product.getStock() <= 0;
    }

    private void updateProductStock(List<ProductEntity> productsAvailable) {
        productsAvailable.forEach(product -> {
            product.setStock(product.getStock() - 1);
            productRepository.save(product);
        });
    }

    private List<String> extractProductNames(List<ProductEntity> productsAvailable) {
        return productsAvailable.stream()
                .map(ProductEntity::getName)
                .toList();
    }
}
