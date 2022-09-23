package com.customer.utils;

import com.customer.dto.CustomerRequestDto;
import com.customer.dto.CustomerResponseDto;
import com.customer.entity.CustomerEntity;

import java.time.LocalDate;

public class CustomerUtils {

    public static CustomerEntity mockCustomerEntity() {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(1);
        entity.setPassword("teste123");
        entity.setCpf("123.456.678-91");
        entity.setBirthdate(LocalDate.now().minusYears(20));
        entity.setFirstName("Jose");
        entity.setLastName("da Silva");
        entity.setEmail("jose@email.com");
        entity.setSex("Masculino");
        entity.setActive(true);

        return entity;
    }

    public static CustomerResponseDto mockCustomerResponse() {
        CustomerResponseDto responseDto = new CustomerResponseDto();
        responseDto.setId(1);
        responseDto.setPassword("teste123");
        responseDto.setCpf("123.456.678-91");
        responseDto.setBirthdate(LocalDate.now().minusYears(20));
        responseDto.setFirstName("Jose");
        responseDto.setLastName("da Silva");
        responseDto.setEmail("jose@email.com");
        responseDto.setSex("Masculino");
        responseDto.setActive(true);
        return responseDto;
    }

    public static CustomerRequestDto mockCustomerRequest() {
        CustomerRequestDto requestDto = new CustomerRequestDto();
        requestDto.setPassword("teste123");
        requestDto.setCpf("123.456.678-91");
        requestDto.setBirthdate(LocalDate.now().minusYears(20));
        requestDto.setFirstName("Jose");
        requestDto.setLastName("da Silva");
        requestDto.setEmail("jose@email.com");
        requestDto.setSex("Masculino");
        requestDto.setActive(true);
        return requestDto;
    }


}
