package desafio.ecommerce.dtos;

import desafio.ecommerce.models.ProductEntity;

public record PostProductDTO(String name, double price, int stock) {

    public ProductEntity dtoToEntity() {
        ProductEntity newProduct = new ProductEntity();
        newProduct.setName(name);
        newProduct.setPrice(price);
        newProduct.setStock(stock);
        return newProduct;
    }
}
