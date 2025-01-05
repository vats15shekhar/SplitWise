package com.scaler.SplitWise.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {
    private String phoneNumber;
    private String realPassword;

    public UserLoginDTO() {}
    public UserLoginDTO(String phoneNumber, String realPassword) {
        this.phoneNumber = phoneNumber;
        this.realPassword = realPassword;
    }
}
