package com.ifsul.exercicio2topicos.models;


import java.util.Date;

public class Customer {

    private Integer id;
    private String name;
    private String phoneNumber;
    private Integer birthDateInMillies;
    private boolean blacklist;
    private Date creationTimestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getBirthDateInMillies() {
        return birthDateInMillies;
    }

    public void setBirthDateInMillies(Integer birthDateInMillies) {
        this.birthDateInMillies = birthDateInMillies;
    }

    public boolean isBlacklist() {
        return blacklist;
    }

    public void setBlacklist(boolean blacklist) {
        this.blacklist = blacklist;
    }

    public Date getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Date creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }
}
