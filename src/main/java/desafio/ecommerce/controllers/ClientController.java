package desafio.ecommerce.controllers;

import desafio.ecommerce.dtos.ClientDTO;
import desafio.ecommerce.dtos.PostClientDTO;
import desafio.ecommerce.exceptions.ClientNotFoundException;
import desafio.ecommerce.services.ClientService;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientDTO> registerClientController(@Valid @RequestBody PostClientDTO newClient) {
        ClientDTO saveNewClient = clientService.registerClientService(newClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveNewClient);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClientDTO> getClientByCpfController(@PathVariable String cpf)
            throws ClientNotFoundException {
        ClientDTO clientByCpf = clientService.getClientByCpfService(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(clientByCpf);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClientFactoryController(
            @NotNull(message = "O nome não pode estar vazio")
            @NotEmpty(message = "O nome não pode ser nulo")
            @CPF(message = "CPF inválido")
            @Email(message = "O email deve ser um endereço de email válido!")
            @Valid @PathVariable Long id,
            @RequestBody PostClientDTO clientUpdated)
            throws ClientNotFoundException {
        ClientDTO updateClient = clientService.updateClientFactoryService(id, clientUpdated);
        return ResponseEntity.status(HttpStatus.OK).body(updateClient);
    }
}
