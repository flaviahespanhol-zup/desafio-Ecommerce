package desafio.ecommerce.services;

import desafio.ecommerce.dtos.PostPurchaseDTO;
import desafio.ecommerce.exceptions.ClientNotFoundException;
import desafio.ecommerce.exceptions.ProductNotFoundException;
import desafio.ecommerce.exceptions.ProductUnstockedException;
import desafio.ecommerce.repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public List<String> shoppingService(PostPurchaseDTO purchase)
            throws ClientNotFoundException, ProductNotFoundException, ProductUnstockedException {
        return purchaseRepository.shopping(purchase);
    }
}
