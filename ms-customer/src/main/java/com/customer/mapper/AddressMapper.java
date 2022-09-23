package com.customer.mapper;

import com.customer.dto.AddressRequestDto;
import com.customer.dto.AddressResponseDto;
import com.customer.entity.AddressEntity;
import com.customer.service.CustomerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CustomerService.class)
public interface AddressMapper {

    @Mapping(source = "customerId", target = "customer")
    AddressEntity requestDtoToEntity(AddressRequestDto requestDto);

    @Mapping(source = "customer.id", target = "customerId")
    AddressResponseDto entityToResponseDto(AddressEntity entity);

}