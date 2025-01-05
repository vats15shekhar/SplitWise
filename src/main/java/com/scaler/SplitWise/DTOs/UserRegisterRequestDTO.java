package com.scaler.SplitWise.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequestDTO {
    private String name;
    private String password;
    private String email;
    private String phone;

    public UserRegisterRequestDTO() {}
    public UserRegisterRequestDTO(String name, String password, String email, String phone) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}
