package desafio.ecommerce.dtos;

import java.util.List;

public record PostPurchaseDTO(String cpf, List<String> products) {
}
