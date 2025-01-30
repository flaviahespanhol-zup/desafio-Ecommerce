package desafio.ecommerce.services;

import desafio.ecommerce.dtos.PostProductDTO;
import desafio.ecommerce.dtos.ProductDTO;
import desafio.ecommerce.services.helper.ProductFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductFactory productFactory;

    public ProductService(ProductFactory productFactory) {
        this.productFactory = productFactory;
    }

    public ProductDTO registerProductService(PostProductDTO newProduct) {
        return productFactory.registerProductFactory(newProduct);
    }
}
