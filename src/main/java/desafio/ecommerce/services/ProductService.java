package desafio.ecommerce.services;

import desafio.ecommerce.dtos.PostProductDTO;
import desafio.ecommerce.dtos.ProductDTO;
import desafio.ecommerce.models.ProductEntity;
import desafio.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO registerProductService(PostProductDTO newProduct) {
        ProductEntity newProductEntity = newProduct.dtoToEntity();
        ProductEntity saveNewProduct = productRepository.save(newProductEntity);
        return ProductDTO.entityToDTO(saveNewProduct);
    }
}
