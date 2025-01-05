package com.scaler.SplitWise.Service;

public interface PasswordEncoder {
    String getEncodedPassword(String realPassword);
    boolean matches(String realPassword, String hashedPassword);
}
