package desafio.ecommerce.controllers;

import desafio.ecommerce.dtos.PostProductDTO;
import desafio.ecommerce.dtos.ProductDTO;
import desafio.ecommerce.exceptions.ProductNotFoundException;
import desafio.ecommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> registerProductController(@Valid @RequestBody PostProductDTO newProduct) {
        ProductDTO saveNewProduct = productService.registerProductService(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveNewProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> listAllProductsController() {
        List<ProductDTO> allProducts = productService.listAllProductsService();
        return ResponseEntity.status(HttpStatus.OK).body(allProducts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductController(@PathVariable Long id) throws ProductNotFoundException {
        productService.deleteProductByIdService(id);
        return ResponseEntity.noContent().build();
    }
}
