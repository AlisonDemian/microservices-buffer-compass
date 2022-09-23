package com.customer.controller;

import com.customer.dto.AddressRequestDto;
import com.customer.entity.AddressEntity;
import com.customer.mapper.AddressMapper;
import com.customer.service.AddressService;
import com.customer.utils.AddressUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(AddressController.class)
class AddressControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AddressService service;

    @MockBean
    AddressMapper mapper;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void whenCreateAddress_withValidRequest_returnStatusCreated() throws Exception {

        when(mapper.requestDtoToEntity(any(AddressRequestDto.class)))
                .thenReturn(AddressUtils.mockAddressEntity());

        when(service.save(any(AddressEntity.class)))
                .thenReturn(AddressUtils.mockAddressEntity());

        when(mapper.entityToResponseDto(any(AddressEntity.class)))
                .thenReturn(AddressUtils.mockAddressResponseDto());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(AddressUtils.mockAddressRequestDto()));

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.CREATED.value());
    }

    @Test
    void whenUpdateAddress_withValidRequestAndId_returnStatusOk() throws Exception {
        when(mapper.requestDtoToEntity(any(AddressRequestDto.class)))
                .thenReturn(AddressUtils.mockAddressEntity());

        when(service.update(anyInt(), any(AddressEntity.class)))
                .thenReturn(AddressUtils.mockAddressEntity());

        when(mapper.entityToResponseDto(any(AddressEntity.class)))
                .thenReturn(AddressUtils.mockAddressResponseDto());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/address/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(AddressUtils.mockAddressRequestDto()));

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }

    @Test
    void whenDeleteAddress_withValidId_returnStatusOk() throws Exception{
        int id = anyInt();

        service.delete(id);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/address/{id}", id)
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }
}