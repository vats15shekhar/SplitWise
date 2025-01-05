package com.scaler.SplitWise.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoder implements PasswordEncoder{

    private BCryptPasswordEncoder springBCrpytEncoder = new BCryptPasswordEncoder();

    @Override
    public String getEncodedPassword(String realPassword) {
        return springBCrpytEncoder.encode(realPassword);
    }

    @Override
    public boolean matches(String realPassword, String hashedPassword) {
        return springBCrpytEncoder.matches(realPassword, hashedPassword);
    }
}

/*
Check out the below for an understanding of BCryptEncoder

https://docs.google.com/document/d/1L4312IVtR8iqY43ZV5_7gOg5VBr3tEVQ8aTiJbbFNyk/edit?tab=t.ix71wb7wduxd
 */
