package com.customer.service;

import com.customer.entity.AddressEntity;
import com.customer.entity.CustomerEntity;
import com.customer.exception.BusinessException;
import com.customer.repository.AddressRepository;
import com.customer.utils.AddressUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.customer.utils.AddressUtils.mockAddressEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @InjectMocks
    AddressService service;

    @Mock
    AddressRepository repository;

    @Test
    void whenSave_withSuccess_returnEntity() {
        AddressEntity entity = mockAddressEntity();

        when(repository.save(any(AddressEntity.class))).thenReturn(entity);

        assertThat(service.save(entity))
                .isNotNull()
                .hasNoNullFieldsOrProperties()
                .isInstanceOf(AddressEntity.class);
    }

    @Test
    void whenGetAddressById_withIncorrectId_throwsException() {
        Integer id = 123;
        when(repository.findById(anyInt())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getAddressById(id))
                .isInstanceOf(BusinessException.class)
                .hasMessage(String.format("address %d not found", id));
    }

    @Test
    void whenUpdate_withSuccess_returnUpdatedEntity() {
        Integer id = 123;
        AddressEntity entity = AddressUtils.mockAddressEntity();
        AddressEntity updatedEntity = entity;
        int newNumber = 11111;
        updatedEntity.setNumber(newNumber);

        when(repository.findById(anyInt())).thenReturn(Optional.of(entity));
        when(repository.save(any(AddressEntity.class))).thenReturn(updatedEntity);

        assertThat(service.update(id, updatedEntity))
                .isNotNull()
                .hasNoNullFieldsOrProperties()
                .hasFieldOrPropertyWithValue("number", newNumber);
    }

    @Test
    void whenDelete_withValidId() {
        AddressEntity entity = AddressUtils.mockAddressEntity();

        when(repository.findById(anyInt())).thenReturn(Optional.of(entity));

        int id = 123;
        service.delete(id);
        verify(repository, times(1)).deleteById(id);
    }
}