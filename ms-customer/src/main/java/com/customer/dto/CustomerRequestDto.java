package com.customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class CustomerRequestDto {

    @NotBlank(message = "cpf is required")
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$", message = "cpf is not valid")
    private String cpf;

    @NotBlank(message = "first name is required")
    @Size(min = 3, message = "first name must contain at least 3 characters")
    private String firstName;

    @NotBlank(message = "last name is required")
    @Size(min = 3, message = "last name must contain at least 3 characters")
    private String lastName;

    @NotBlank(message = "sex is required")
    @Size(min = 8, max = 9, message = "sex must be Masculino or Feminino")
    @Pattern(regexp = "^Masculino|Feminino$", message = "sex must be Masculino or Feminino")
    private String sex;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    @NotBlank(message = "email is required")
    @Email
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 6, message = "password must contain at least 6 characters")
    private String password;

    @NotNull
    private boolean active;

    public CustomerRequestDto(String cpf, String firstName, String lastName, String sex, LocalDate birthdate, String email, String password, boolean active) {
        this.cpf = cpf;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        this.active = active;
    }

    public CustomerRequestDto() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
