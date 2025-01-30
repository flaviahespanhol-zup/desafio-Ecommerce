package desafio.ecommerce.services.helper;

import desafio.ecommerce.dtos.PostProductDTO;
import desafio.ecommerce.dtos.ProductDTO;
import desafio.ecommerce.models.ProductEntity;
import desafio.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public List<ProductDTO> listAllProducts() {
        List<ProductEntity> allProducts = productRepository.findAll();
        return allProducts.stream()
                .map(ProductDTO::entityToDTO).toList();
    }

}
