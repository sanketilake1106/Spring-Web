package com.maven.services;

import com.maven.entities.Address;
import com.maven.entities.User;

import java.io.Serializable;
import java.util.List;

public interface AddressService {

    Serializable addAddress(Address address);

    Address getSingleAddress(Address address);

    List<Address> getAll();
    Address getAddress(Long id);

    void updateAddress(Address address);

    void update1Address(Address address);

}
