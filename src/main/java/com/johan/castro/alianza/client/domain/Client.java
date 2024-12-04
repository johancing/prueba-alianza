package com.johan.castro.alianza.client.domain;

import java.util.Date;

public class Client {

    private String sharedKey;
    private String name;
    private String email;
    private String phone;
    private Date dateAdded;
    private Date dateFinish;

    public Client(String name, String email, String phone, Date dateAdded, Date dateFinish) {
        createSharedKey(name);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dateAdded = dateAdded;
        this.dateFinish = dateFinish;
    }

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
        createSharedKey(name);
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

    private void createSharedKey(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cant be null or empty");
        }
        String[] parts = name.split(" ");
        if (parts.length < 2)
            throw new IllegalArgumentException("name need two parts");
        String sharedKey = parts[0].substring(0, 1) + parts[1];
        setSharedKey(sharedKey.toLowerCase());
    }
}
