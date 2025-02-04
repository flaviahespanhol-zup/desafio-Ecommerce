package desafio.ecommerce.dtos;

import desafio.ecommerce.models.ProductEntity;

public record ProductDTO(Long id, String name, double price, int stock) {

    public static ProductDTO entityToDTO (ProductEntity product) {
        return new ProductDTO(
                product.getId(), product.getName(), product.getPrice(),
                product.getStock());
    }
}
