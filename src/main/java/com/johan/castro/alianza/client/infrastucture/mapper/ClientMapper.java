package com.johan.castro.alianza.client.infrastucture.mapper;

import com.johan.castro.alianza.client.domain.Client;
import com.johan.castro.alianza.client.infrastucture.persistence.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public static ClientEntity domainToEntity(Client client) {
        if (client == null)
            return null;
        return new ClientEntity(
                client.getSharedKey(),
                client.getName(),
                client.getEmail(),
                client.getPhone(),
                client.getDateAdded(),
                client.getDateFinish()
                );
    }

    public static Client entityToDomain(ClientEntity client) {
        if (client == null)
            return null;
        return new Client(
                client.getName(),
                client.getEmail(),
                client.getPhone(),
                client.getDateAdded(),
                client.getDateFinish()
        );
    }

    public static void update(Client client, ClientEntity entity) {
        if (client == null || entity == null    )
            return;
        if (client.getName() != null)
            entity.setName(client.getName());
        if (client.getEmail() != null)
            entity.setEmail(client.getEmail());
        if (client.getPhone() != null)
            entity.setPhone(client.getPhone());
        if (client.getDateAdded() != null)
            entity.setDateAdded(client.getDateAdded());
        if (client.getDateFinish() != null)
            entity.setDateFinish(client.getDateFinish());
    }
}
