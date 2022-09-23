package com.customer.dto;


import com.customer.entity.CustomerEntity;

public class AddressResponseDto {

    private Integer customerId;
    private String state;
    private String city;
    private String district;
    private String street;
    private Integer number;
    private String cep;
    private String complement;

    public AddressResponseDto(Integer customerId, String state, String city, String district, String street, Integer number, String cep, String complement) {
        this.customerId = customerId;
        this.state = state;
        this.city = city;
        this.district = district;
        this.street = street;
        this.number = number;
        this.cep = cep;
        this.complement = complement;
    }

    public AddressResponseDto() { }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }
}
