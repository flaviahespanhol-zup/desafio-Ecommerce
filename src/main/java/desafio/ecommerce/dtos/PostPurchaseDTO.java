package desafio.ecommerce.dtos;

import java.util.List;

public record PostPurchaseDTO(String cpf, List<PurchaseProductDTO> products) {
    public static record PurchaseProductDTO(String name) {
    }
}
