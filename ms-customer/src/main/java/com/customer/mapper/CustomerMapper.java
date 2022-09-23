package com.customer.mapper;

import com.customer.dto.CustomerRequestDto;
import com.customer.dto.CustomerResponseDto;
import com.customer.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerEntity requestDtoToEntity(CustomerRequestDto requestDto);

    CustomerResponseDto entityToResponseDto(CustomerEntity entity);

}
