package desafio.ecommerce.dtos;

import desafio.ecommerce.models.ProductEntity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

public record PostProductDTO(
        @Column(unique = true)
        @NotEmpty(message = "O nome não pode estar vazio")
        @NotNull(message = "O nome não pode ser nulo")
        String name,
        @NotNull(message = "O preço não pode ser nulo")
        @DecimalMin(value = "0.01", inclusive = true, message = "O preço deve ser maior que zero")
        double price,
        @NotNull(message = "O estoque não pode ser nulo")
        @Min(value = 0, message = "O estoque deve ser um número inteiro maior ou igual a zero")
        int stock) {

    public ProductEntity dtoToEntity() {
        ProductEntity newProduct = new ProductEntity();
        newProduct.setName(name);
        newProduct.setPrice(price);
        newProduct.setStock(stock);
        return newProduct;
    }
}
