package com.johan.castro.alianza.client.infrastucture.persistence;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cliente", schema = "alianza")
public class ClientEntity {

    @Id
    @Column(name = "shared_key", nullable = false)
    private String sharedKey;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "date_added", nullable = false)
    private Date dateAdded;
    @Column(name = "date_finish")
    private Date dateFinish;

    public ClientEntity(String sharedKey, String name, String email, String phone, Date dateAdded, Date dateFinish) {
        this.sharedKey = sharedKey;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dateAdded = dateAdded;
        this.dateFinish = dateFinish;
    }

    public ClientEntity() {}

    public String getSharedKey() {
        return sharedKey;
    }

    public void setSharedKey(String sharedKey) {
        this.sharedKey = sharedKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }
}
