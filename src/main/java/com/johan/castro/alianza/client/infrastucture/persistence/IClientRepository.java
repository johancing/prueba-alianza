package com.johan.castro.alianza.client.infrastucture.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IClientRepository extends JpaRepository<ClientEntity, String> {

    public ClientEntity findBySharedKey(String sharedKey);

    @Query(value = "SELECT * FROM alianza.cliente WHERE date_finish is null", nativeQuery = true)
    public List<ClientEntity> findAllCurrentClients();

    @Query(value = "SELECT * FROM alianza.cliente WHERE date_finish is not null", nativeQuery = true)
    public List<ClientEntity> findAllRetiredClients();
}
