package com.customer.controller;

import com.customer.dto.CustomerRequestDto;
import com.customer.entity.CustomerEntity;
import com.customer.mapper.CustomerMapper;
import com.customer.service.CustomerService;
import com.customer.utils.CustomerUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.customer.utils.CustomerUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @MockBean
    CustomerService service;

    @MockBean
    CustomerMapper mapper;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void init() {
        objectMapper.findAndRegisterModules();
    }

    @Test
    void createCustomer() throws Exception {
        when(mapper.requestDtoToEntity(any(CustomerRequestDto.class)))
                .thenReturn(mockCustomerEntity());
        when(service.save(any(CustomerEntity.class)))
                .thenReturn(mockCustomerEntity());
        when(mapper.entityToResponseDto(any(CustomerEntity.class)))
                .thenReturn(mockCustomerResponse());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockCustomerRequest()));

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void getCustomer() throws Exception {
        Integer id = anyInt();

        when(service.getCustomerById(id))
                .thenReturn(mockCustomerEntity());
        when(mapper.entityToResponseDto(any(CustomerEntity.class)))
                .thenReturn(mockCustomerResponse());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/{id}", id);

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void updateCustomer() throws Exception {

        when(mapper.requestDtoToEntity(any(CustomerRequestDto.class)))
                .thenReturn(mockCustomerEntity());
        when(service.update(anyInt(), any(CustomerEntity.class)))
                .thenReturn(mockCustomerEntity());
        when(mapper.entityToResponseDto(any(CustomerEntity.class)))
                .thenReturn(mockCustomerResponse());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/customers/{id}", anyInt())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockCustomerRequest()));

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void updatePassword() throws Exception {
        Integer id = 123;
        String senha = "teste123*";

        service.updatePassword(id, senha);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/customers/{id}/password", id)
                .contentType(MediaType.APPLICATION_JSON)
                .param("password", senha);

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}