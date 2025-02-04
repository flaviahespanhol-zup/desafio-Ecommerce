package desafio.ecommerce.controllers;

import desafio.ecommerce.dtos.ClientDTO;
import desafio.ecommerce.dtos.PostClientDTO;
import desafio.ecommerce.exceptions.ClientNotFoundException;
import desafio.ecommerce.services.ClientService;
import jakarta.validation.Valid;
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
            @PathVariable Long id,
            @RequestBody @Valid PostClientDTO clientUpdated)
            throws ClientNotFoundException {
        ClientDTO updateClient = clientService.updateClientService(id, clientUpdated);
        return ResponseEntity.status(HttpStatus.OK).body(updateClient);
    }
}
