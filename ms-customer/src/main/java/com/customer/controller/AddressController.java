package com.customer.controller;

import com.customer.dto.AddressRequestDto;
import com.customer.dto.AddressResponseDto;
import com.customer.mapper.AddressMapper;
import com.customer.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = "/address")
@RestController
public class AddressController {

    private final AddressService service;
    private final AddressMapper mapper;

    public AddressController(AddressService service, AddressMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<AddressResponseDto> createAddress(@Valid @RequestBody AddressRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.entityToResponseDto(service.save(mapper.requestDtoToEntity(requestDto))));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AddressResponseDto> updateAddress(@PathVariable Integer id, @Valid @RequestBody AddressRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.entityToResponseDto(service.update(id, mapper.requestDtoToEntity(requestDto))));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<AddressResponseDto> deleteAddress(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK).build();
    }

}