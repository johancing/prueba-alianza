package com.johan.castro.alianza.client.application;

import com.johan.castro.alianza.client.domain.Client;
import java.util.List;

public interface IClientService {

    public Client searchBySharedKey(String sharedKey);
    public List<Client> searchCurrentClients();
    public List<Client> searchRetiredClients();
    public List<Client> getClients();
    public Client save(Client client);
    public Client update(String sharedKey, Client client);

}
