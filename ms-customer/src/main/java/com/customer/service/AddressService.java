package com.customer.service;

import com.customer.entity.AddressEntity;
import com.customer.exception.BusinessException;
import com.customer.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    public AddressEntity save(AddressEntity entity) {
        return repository.save(entity);
    }

    public AddressEntity getAddressById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException(String.format("address %d not found", id)));
    }

    public AddressEntity update(Integer id, AddressEntity updatedEntity) {
        getAddressById(id);
        updatedEntity.setId(id);
        return save(updatedEntity);
    }

    public void delete(Integer id) {
        getAddressById(id);
        repository.deleteById(id);
    }
}
