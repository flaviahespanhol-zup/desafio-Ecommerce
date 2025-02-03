package desafio.ecommerce.services.helper;

import desafio.ecommerce.dtos.PostPurchaseDTO;
import desafio.ecommerce.exceptions.ClientNotFoundException;
import desafio.ecommerce.exceptions.ProductNotFoundException;
import desafio.ecommerce.models.ProductEntity;
import desafio.ecommerce.repositories.ClientRepository;
import desafio.ecommerce.repositories.ProductRepository;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PurchaseFactory {

    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public PurchaseFactory(ClientRepository clientRepository, ProductRepository productRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    public List<String> shoppingFactory(PostPurchaseDTO purchase) throws ClientNotFoundException, ProductNotFoundException {
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

    private void processProducts(PostPurchaseDTO purchase, List<String> productsNotAvailable, List<ProductEntity> productsAvailable) throws ProductNotFoundException {
        for (PostPurchaseDTO.PurchaseProductDTO productDTO : purchase.products()) {
            String productName = productDTO.name();
            ProductEntity product = findProductByName(productName);

            if (isProductOutOfStock(product)) {
                productsNotAvailable.add(productName);
            } else {
                productsAvailable.add(product);
            }
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

