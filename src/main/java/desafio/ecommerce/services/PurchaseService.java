package desafio.ecommerce.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import desafio.ecommerce.dtos.PostPurchaseDTO;
import desafio.ecommerce.exceptions.ClientNotFoundException;
import desafio.ecommerce.exceptions.ProductNotFoundException;
import desafio.ecommerce.exceptions.ProductUnstockedException;
import desafio.ecommerce.services.helper.PurchaseFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseFactory purchaseFactory;

    public PurchaseService(PurchaseFactory purchaseFactory) {
        this.purchaseFactory = purchaseFactory;
    }

    public List<String> shoppingService(PostPurchaseDTO purchase)
            throws ClientNotFoundException, ProductNotFoundException, ProductUnstockedException {
        return purchaseFactory.shoppingFactory(purchase);
    }
}
