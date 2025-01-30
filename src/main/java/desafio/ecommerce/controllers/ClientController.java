package desafio.ecommerce.controllers;

import desafio.ecommerce.dtos.ClientDTO;
import desafio.ecommerce.dtos.PostClientDTO;
import desafio.ecommerce.services.ClientService;
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
    public ResponseEntity<ClientDTO> registerClientController(@RequestBody PostClientDTO newClient) {
        ClientDTO saveNewClient = clientService.registerClientService(newClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveNewClient);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClientDTO> getClientByCpfController(@PathVariable String cpf) {
        ClientDTO clientByCpf = clientService.getClientByCpfService(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(clientByCpf);
    }
}
