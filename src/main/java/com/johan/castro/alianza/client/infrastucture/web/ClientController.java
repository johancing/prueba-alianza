package com.johan.castro.alianza.client.infrastucture.web;

import com.johan.castro.alianza.client.application.AppValidator;
import com.johan.castro.alianza.client.application.ApplicationException;
import com.johan.castro.alianza.client.domain.Client;
import com.johan.castro.alianza.client.infrastucture.service.ClientService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/alianza/client")
public class ClientController {

    private final Logger log = LogManager.getLogger(ClientController.class);
    private final String INTERNAL_ERROR = "Internal server error.";
    private final String NO_CONTENT = "No content.";
    private final String SUCCESS = "Success.";
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseClient> getAll() {
        ResponseClient response;
        try {
            List<Client> clients = service.getClients();
            if (clients.isEmpty()) {
                response = new ResponseClient(NO_CONTENT, 204, null);
            } else {
                response = new ResponseClient(SUCCESS, 200, clients);
            }
            return ResponseEntity.ok(response);
        } catch (ApplicationException ae) {
            log.error(ae.getMessage());
            return ResponseEntity.status(201).body(new ResponseClient(ae.getMessage(), 201, null));
        } catch (Exception e) {
             log.error(e.getMessage(), e);
             return ResponseEntity.status(500).body(new ResponseClient(INTERNAL_ERROR, 500, null));
        }
    }

    @GetMapping("/allCurrentClients")
    public ResponseEntity<ResponseClient> searchAllCurrentClients() {
        ResponseClient response;
        try {
            List<Client> clients = service.searchCurrentClients();
            if (clients.isEmpty()) {
                response = new ResponseClient(NO_CONTENT, 204, null);
            } else {
                response = new ResponseClient(SUCCESS, 200, clients);
            }
            return ResponseEntity.ok(response);
        } catch (ApplicationException ae) {
            log.error(ae.getMessage());
            return ResponseEntity.status(201).body(new ResponseClient(ae.getMessage(), 201, null));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(new ResponseClient(INTERNAL_ERROR, 500, null));
        }
    }

    @GetMapping("/allRetiredClients")
    public ResponseEntity<ResponseClient> searchAllRetiredClients() {
        ResponseClient response;
        try {
            List<Client> clients = service.searchRetiredClients();
            if (clients.isEmpty()) {
                response = new ResponseClient(NO_CONTENT, 204, null);
            } else {
                response = new ResponseClient(SUCCESS, 200, clients);
            }
            return ResponseEntity.ok(response);
        } catch (ApplicationException ae) {
            log.error(ae.getMessage());
            return ResponseEntity.status(201).body(new ResponseClient(ae.getMessage(), 201, null));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(new ResponseClient(INTERNAL_ERROR, 500, null));
        }
    }

    @GetMapping("/{sharedKey}")
    public ResponseEntity<ResponseClient> getClient(@PathVariable String sharedKey) {
        ResponseClient response;
        try {
            Client client = service.searchBySharedKey(sharedKey);
            if (client == null) {
                response = new ResponseClient(NO_CONTENT, 204, null);
            } else {
                response = new ResponseClient(SUCCESS, 200, client);
            }
            return ResponseEntity.ok(response);
        } catch (ApplicationException ae) {
            log.error(ae.getMessage());
            return ResponseEntity.status(201).body(new ResponseClient(ae.getMessage(), 201, null));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(new ResponseClient(INTERNAL_ERROR, 500, null));
        }
    }

    @PostMapping("/")
    public ResponseEntity<ResponseClient> createClient(@RequestBody Client client) {
        ResponseClient response;
        List<String> errors = AppValidator.validate(client);
        if (errors != null)
            return ResponseEntity.status(400).body(new ResponseClient("BAD_REQUEST", 400, errors));
        try {
            client = service.save(client);
            if (client == null) {
                response = new ResponseClient(NO_CONTENT, 204, null);
            } else {
                response = new ResponseClient(SUCCESS, 200, client);
            }
            return ResponseEntity.ok(response);
        } catch (ApplicationException ae) {
            log.error(ae.getMessage());
            return ResponseEntity.status(201).body(new ResponseClient(ae.getMessage(), 201, null));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(new ResponseClient(INTERNAL_ERROR, 500, null));
        }
    }

    @PutMapping("/{sharedKey}")
    public ResponseEntity<ResponseClient> updateClient(@PathVariable String sharedKey, @RequestBody Client client) {
        ResponseClient response;
        List<String> errors = AppValidator.validate(client);
        if (errors != null)
            return ResponseEntity.status(400).body(new ResponseClient("BAD_REQUEST", 400, errors));
        try {
            client = service.update(sharedKey, client);
            if (client == null) {
                response = new ResponseClient(NO_CONTENT, 204, null);
            } else {
                response = new ResponseClient(SUCCESS, 200, client);
            }
            return ResponseEntity.ok(response);
        } catch (ApplicationException ae) {
            log.error(ae.getMessage());
            return ResponseEntity.status(201).body(new ResponseClient(ae.getMessage(), 201, null));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(new ResponseClient(INTERNAL_ERROR, 500, null));
        }
    }
}
