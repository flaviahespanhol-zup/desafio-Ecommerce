package desafio.ecommerce.services.helper;

import desafio.ecommerce.dtos.PostProductDTO;
import desafio.ecommerce.dtos.ProductDTO;
import desafio.ecommerce.models.ProductEntity;
import desafio.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductFactory {
    private final ProductRepository productRepository;

    public ProductFactory(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO registerProductFactory(PostProductDTO newProduct) {
        ProductEntity newProductEntity = newProduct.dtoToEntity();
        ProductEntity saveNewProduct = productRepository.save(newProductEntity);
        return ProductDTO.entityToDTO(saveNewProduct);
    }

}
