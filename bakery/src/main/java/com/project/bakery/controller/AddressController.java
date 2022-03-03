package com.project.bakery.controller;

import com.project.bakery.model.Address;
import com.project.bakery.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController()
@RequestMapping("/api/address")
public class AddressController {

    AddressService addressService;

    AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @GetMapping("/address/{user_id}")
    public List<Address> getAllUsers(@PathVariable(value="user_id") String id){
        System.out.println("samut");
        return addressService.getAddress(id);
    }

    @PostMapping("/save")
    public void saveAddress(@RequestBody Address address){
        addressService.saveAddress(address);
    }

    @PostMapping("/delete")
    public void deleteAddress(@RequestBody Address address){
        addressService.deleteAddress(address);
    }

    @PutMapping("/update")
    public Address updateAddress(@RequestBody Address address) {
        return addressService.updateAddress(address);
    }
}