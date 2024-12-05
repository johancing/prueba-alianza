package com.johan.castro.alianza.client.infrastucture.service;

import com.johan.castro.alianza.client.application.ApplicationException;
import com.johan.castro.alianza.client.application.IClientService;
import com.johan.castro.alianza.client.application.LoggerParams;
import com.johan.castro.alianza.client.domain.Client;
import com.johan.castro.alianza.client.infrastucture.mapper.ClientMapper;
import com.johan.castro.alianza.client.infrastucture.persistence.ClientEntity;
import com.johan.castro.alianza.client.infrastucture.persistence.IClientRepository;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@Service
public class ClientService implements IClientService {

    private final Logger log = LogManager.getLogger(ClientService.class);
    private final IClientRepository repo;

    @Autowired
    public ClientService(IClientRepository repo) {
        this.repo = repo;
        LoggerParams.getInstance().build("localhost", "johanc.ing@gmail.com", "GMT-5");
        LoggerParams.getInstance().setEvent("Client service");
    }

    @Override
    public Client searchBySharedKey(String sharedKey) {
        log.log(Level.INFO, "Search by SharedKey: " + sharedKey);
        return ClientMapper.entityToDomain(repo.findBySharedKey(sharedKey));
    }

    @Override
    public List<Client> searchCurrentClients() {
        log.log(Level.INFO, "Search all current clients.");
        List<ClientEntity> entities = repo.findAllCurrentClients();
        return entities.stream()
                .map(e -> new Client(e.getName(), e.getEmail(), e.getPhone(), e.getDateAdded(), e.getDateFinish()))
                .toList();
    }

    @Override
    public List<Client> searchRetiredClients() {
        log.log(Level.INFO, "Search all retired clients");
        List<ClientEntity> entities = repo.findAllRetiredClients();
        return entities.stream()
                .map(e -> new Client(e.getName(), e.getEmail(), e.getPhone(), e.getDateAdded(), e.getDateFinish()))
                .toList();
    }

    @Override
    public List<Client> getClients() {
        log.log(Level.INFO, "Search all clients.");
        List<ClientEntity> entities = repo.findAll();
        return entities.stream()
                .map(e -> new Client(e.getName(), e.getEmail(), e.getPhone(), e.getDateAdded(), e.getDateFinish()))
                .toList();
    }

    @Override
    public Client save(Client client) {
        ClientEntity entity = repo.findBySharedKey(client.getSharedKey());
        if (entity != null)
            throw new ApplicationException("Client already exists");
        entity = ClientMapper.domainToEntity(client);
        log.log(Level.INFO, "Save client with sharedKey " + client.getSharedKey());
        entity = repo.save(entity);
        return ClientMapper.entityToDomain(entity);
    }

    @Override
    public Client update(String sharedKey, Client client) {
        log.log(Level.INFO, "Update client with sharedKey " + sharedKey);
        ClientEntity entity = repo.findBySharedKey(sharedKey);
        if (entity == null)
            return null;
        ClientMapper.update(client, entity);
        entity = repo.save(entity);
        client = ClientMapper.entityToDomain(entity);
        client.setSharedKey(entity.getSharedKey());
        return client;
    }
}
