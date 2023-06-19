package assitenciatecnica.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import assitenciatecnica.backend.model.Client;
import assitenciatecnica.backend.repository.ClientRepository;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    // create client
    public void createClient(Client client) {
        clientRepository.save(client);
    }

    // update client
    public ResponseEntity<?> updateClient(Long ClientId, Client client) {
        if (clientRepository.findById(ClientId).isPresent()) {
            Client clientToUpdate = clientRepository.findById(ClientId).get();
            clientToUpdate.setName(client.getName());
            clientToUpdate.setCpf(client.getCpf());
            clientToUpdate.setAddress(client.getAddress());
            clientToUpdate.setPhone(client.getPhone());
            clientToUpdate.setEmail(client.getEmail());
            clientToUpdate.setDefect(client.getDefect());
            clientToUpdate.setProduct_type(client.getProduct_type());
            clientRepository.save(clientToUpdate);
            return ResponseEntity.ok().body("Cliente atualizado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }
    }

    // get all clients
    public ResponseEntity<?> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        if (clients.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum cliente encontrado!");
        }

        return ResponseEntity.ok().body(clients);
    }

    // get client by id
    public ResponseEntity<?> getClientById(Long clientId) {
        if (clientRepository.existsById(clientId)) {
            Client client = clientRepository.findById(clientId).get();
            return ResponseEntity.ok().body(client);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }
    }

    // delete client by id
    public ResponseEntity<?> deleteClient(Long clientId) {
        if (clientRepository.existsById(clientId)) {
            clientRepository.deleteById(clientId);
            return ResponseEntity.ok().body("Cliente deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }
    }

}
