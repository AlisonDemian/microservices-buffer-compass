package com.customer.service;

import com.customer.entity.CustomerEntity;
import com.customer.exception.BusinessException;
import com.customer.repository.CustomerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository repository;

    private final PasswordEncoder encoder;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
        encoder = new BCryptPasswordEncoder();
    }

    public CustomerEntity save(CustomerEntity entity) {
        entity.setPassword(encryptPassword(entity.getPassword()));
        return repository.save(entity);
    }

    public CustomerEntity getCustomerById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException(String.format("user %d not found", id)));
    }

    public CustomerEntity update(Integer id, CustomerEntity updatedEntity) {
        getCustomerById(id);
        updatedEntity.setId(id);
        updatedEntity.setPassword(encryptPassword(updatedEntity.getPassword()));
        return repository.save(updatedEntity);
    }

    public void updatePassword(Integer id, String updatedPassword) {
        CustomerEntity entity = getCustomerById(id);
        entity.setPassword(encryptPassword(updatedPassword));
        repository.save(entity);
    }

    private String encryptPassword(String txtPassword) {
        return encoder.encode(txtPassword);
    }

}