package com.example.booksapplication.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
public class UserRegisterDto {

    @NotNull
    @NotBlank
    private String firstName;

    @NotBlank
    @NotNull
    private String lastName;

    @NotNull
    @Min(value = 12)
    @Max(value = 80)
    private Integer age;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 16)
    private String username;


    @NotNull
    @NotBlank
    @Size(min = 6, max = 16)
    private String password;


    @Email
    @Size(min = 14, max = 26)
    @NotNull
    private String email;

}
