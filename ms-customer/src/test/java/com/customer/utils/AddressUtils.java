package com.customer.utils;

import com.customer.dto.AddressRequestDto;
import com.customer.dto.AddressResponseDto;
import com.customer.entity.AddressEntity;

public class AddressUtils {

    public static AddressEntity mockAddressEntity() {
        AddressEntity entity = new AddressEntity();
        entity.setId(1);
        entity.setCep("12345-678");
        entity.setCity("Cidade Teste");
        entity.setState("Teste");
        entity.setStreet("Teste");
        entity.setDistrict("Teste");
        entity.setComplement("Teste");
        entity.setNumber(123);
        entity.setCustomer(CustomerUtils.mockCustomerEntity());

        return entity;
    }

    public static AddressRequestDto mockAddressRequestDto() {
        AddressRequestDto dto  = new AddressRequestDto();
        dto.setCep("12345-678");
        dto.setCity("Cidade Teste");
        dto.setState("Teste");
        dto.setStreet("Teste");
        dto.setDistrict("Teste");
        dto.setComplement("Teste");
        dto.setNumber(123);
        dto.setCustomerId(1);

        return dto;
    }

    public static AddressResponseDto mockAddressResponseDto() {
        AddressResponseDto dto  = new AddressResponseDto();
        dto.setCep("12345-678");
        dto.setCity("Cidade Teste");
        dto.setState("Teste");
        dto.setStreet("Teste");
        dto.setDistrict("Teste");
        dto.setComplement("Teste");
        dto.setNumber(123);
        dto.setCustomerId(1);

        return dto;
    }
}
