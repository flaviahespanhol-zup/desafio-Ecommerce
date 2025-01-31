package desafio.ecommerce.services.helper;

import org.springframework.stereotype.Component;

@Component
public class PurchaseFactory {

    private final PurchaseFactory purchaseFactory;

    public PurchaseFactory(PurchaseFactory purchaseFactory) {
        this.purchaseFactory = purchaseFactory;
    }
}
