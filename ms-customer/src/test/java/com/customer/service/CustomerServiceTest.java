package com.customer.service;

import com.customer.entity.CustomerEntity;
import com.customer.exception.BusinessException;
import com.customer.repository.CustomerRepository;
import com.customer.utils.CustomerUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.customer.utils.CustomerUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    CustomerService service;

    @Mock
    CustomerRepository repository;

    PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    void whenSave_withSucess_returnEntity() {
        CustomerEntity entity = mockCustomerEntity();
        when(repository.save(any(CustomerEntity.class))).thenReturn(entity);

        assertThat(service.save(entity))
                .isNotNull()
                .hasNoNullFieldsOrProperties()
                .isInstanceOf(CustomerEntity.class);
    }

    @Test
    void whenGetCustomerById_withIncorrectId_throwsException() {
        Integer id = anyInt();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getCustomerById(id))
                .isInstanceOf(BusinessException.class)
                .hasMessage(String.format("user %d not found", id));
    }

    @Test
    void whenUpdate_withValidIdAndAttributes_returnCustomerEntity() {
        CustomerEntity entity = mockCustomerEntity();
        CustomerEntity updatedEntity = mockCustomerEntity();
        updatedEntity.setFirstName("Updated First Name");
        updatedEntity.setEmail("updated@email.com");
        Integer id = anyInt();

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(repository.save(updatedEntity)).thenReturn(updatedEntity);

        assertThat(service.update(id, updatedEntity))
                .hasFieldOrPropertyWithValue("firstName","Updated First Name")
                .hasFieldOrPropertyWithValue("email", "updated@email.com");
    }

    @Test
    void updatePassword() {
        Integer id = anyInt();
        CustomerEntity updatedEntity = mockCustomerEntity();
        updatedEntity.setPassword(encoder.encode("test123"));

        when(repository.findById(id)).thenReturn(Optional.of(updatedEntity));
        when(repository.save(any(CustomerEntity.class))).thenReturn(updatedEntity);

        service.updatePassword(id, anyString());

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(updatedEntity);
    }
}