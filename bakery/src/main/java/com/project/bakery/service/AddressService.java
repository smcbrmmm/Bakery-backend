package com.project.bakery.service;

import com.project.bakery.model.Address;
import com.project.bakery.repository.AddressRepository;
import com.project.bakery.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    AddressRepository repository;

    public AddressService(AddressRepository addressRepository){
        this.repository = addressRepository;
    }

    public List<Address> getAddress(String id){
        return repository.findAll(id);
    }

    public void saveAddress(Address address){
        repository.save(address);
    }

    public void deleteAddress(Address address){
        repository.delete(address);
    }

    public Address updateAddress(Address address) {
        return repository.update(address);
    }

    public Address getOrderAddress(String addressId) {
        return repository.getOrderAddress(addressId);
    }
}
