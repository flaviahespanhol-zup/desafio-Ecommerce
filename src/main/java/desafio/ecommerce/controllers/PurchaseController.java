package desafio.ecommerce.controllers;

import desafio.ecommerce.dtos.PostPurchaseDTO;
import desafio.ecommerce.exceptions.ClientNotFoundException;
import desafio.ecommerce.exceptions.ProductNotFoundException;
import desafio.ecommerce.exceptions.ProductUnstockedException;
import desafio.ecommerce.services.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity<List<String>> shoppingController(@RequestBody PostPurchaseDTO purchase)
            throws ClientNotFoundException, ProductNotFoundException, ProductUnstockedException {
        List<String> productsName = purchaseService.shoppingService(purchase);
        return ResponseEntity.status(HttpStatus.CREATED).body(productsName);
    }
}