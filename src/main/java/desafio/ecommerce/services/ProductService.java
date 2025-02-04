package desafio.ecommerce.services;

import desafio.ecommerce.dtos.PostProductDTO;
import desafio.ecommerce.dtos.ProductDTO;
import desafio.ecommerce.exceptions.ProductNotFoundException;
import desafio.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO registerProductService(PostProductDTO newProduct) {
        return productRepository.registerProduct(newProduct);
    }

    public List<ProductDTO> listAllProductsService() {
        return productRepository.listAllProducts();
    }

    public void deleteProductByIdService(Long id) throws ProductNotFoundException {
        productRepository.deleteProductById(id);
    }

}
