package assitenciatecnica.backend.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import assitenciatecnica.backend.model.Client;
import assitenciatecnica.backend.service.ClientService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // create client
    @PostMapping
    public ResponseEntity<?> createClient(@Valid @RequestBody Client client, BindingResult bindResult) {
        if (bindResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.unprocessableEntity().body(errors);
        }

        clientService.createClient(client);
        return ResponseEntity.ok().body("Cliente criado com sucesso!");
    }

    // update client
    @PutMapping("{ClientId}")
    public ResponseEntity<?> updateClient(@Valid @PathVariable Long ClientId, @RequestBody Client client,
            BindingResult bindResult) {
        if (bindResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.unprocessableEntity().body(errors);
        }

        client.setId(ClientId);
        clientService.updateClient(ClientId, client);
        return ResponseEntity.ok().body("Cliente atualizado com sucesso!");
    }

    // select all clients
    @GetMapping
    public ResponseEntity<?> getAllClients() {
        return clientService.getAllClients();
    }

    // selelct client by id
    @GetMapping("{ClientId}")
    public ResponseEntity<?> getClientById(@PathVariable Long ClientId) {

        return clientService.getClientById(ClientId);
    }

    // delete client by id
    @DeleteMapping("{ClientId}")
    public ResponseEntity<?> deleteClientById(@PathVariable Long ClientId) {
        clientService.deleteClient(ClientId);
        return ResponseEntity.ok().body("Cliente deletado com sucesso!");
    }
}
