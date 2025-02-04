package desafio.ecommerce.repositories;

import desafio.ecommerce.dtos.PostProductDTO;
import desafio.ecommerce.dtos.ProductDTO;
import desafio.ecommerce.exceptions.ProductAlreadyExistsException;
import desafio.ecommerce.exceptions.ProductNotFoundException;
import desafio.ecommerce.models.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    private final ProductRepositoryJPA productRepositoryJPA;

    public ProductRepository(ProductRepositoryJPA productRepositoryJPA) {
        this.productRepositoryJPA = productRepositoryJPA;
    }

    public ProductDTO registerProduct(PostProductDTO newProduct)
            throws ProductAlreadyExistsException {
        boolean nameAlreadyExists = productRepositoryJPA.findByName(newProduct.name()).isPresent();
        if (nameAlreadyExists) {
            throw new ProductAlreadyExistsException();
        }

        ProductEntity newProductEntity = newProduct.dtoToEntity();
        ProductEntity saveNewProduct = productRepositoryJPA.save(newProductEntity);
        return ProductDTO.entityToDTO(saveNewProduct);
    }

    public List<ProductDTO> listAllProducts() {
        List<ProductEntity> allProducts = productRepositoryJPA.findAll();
        return allProducts.stream()
                .map(ProductDTO::entityToDTO).toList();
    }

    public ProductDTO getProductById(Long id) throws ProductNotFoundException {
        ProductEntity entity = productRepositoryJPA.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        return new ProductDTO(entity.getId(), entity.getName(),
                entity.getPrice(), entity.getStock());
    }

    public void deleteProductById(Long id) throws ProductNotFoundException {
        ProductEntity product = productRepositoryJPA.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        productRepositoryJPA.deleteById(product.getId());
    }
}
