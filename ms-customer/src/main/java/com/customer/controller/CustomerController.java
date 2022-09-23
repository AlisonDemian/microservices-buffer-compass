package com.customer.controller;

import com.customer.dto.CustomerRequestDto;
import com.customer.dto.CustomerResponseDto;
import com.customer.mapper.CustomerMapper;
import com.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@RequestMapping(path = "/customers")
@RestController
public class CustomerController {

    private final CustomerService service;

    private final CustomerMapper mapper;

    public CustomerController(CustomerService service, CustomerMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@Valid @RequestBody CustomerRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.entityToResponseDto(service.save(mapper.requestDtoToEntity(requestDto))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomer(@Value("id") @PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.entityToResponseDto(service.getCustomerById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable Integer id,
                                                              @Valid @RequestBody CustomerRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.entityToResponseDto(service.update(id, mapper.requestDtoToEntity(requestDto))));
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<CustomerResponseDto> updatePassword(@PathVariable Integer id,
                                                              @NotBlank @Size(min = 6, message = "password must contain at least 6 characters")
                                                              @RequestParam String password) {
        service.updatePassword(id, password);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
