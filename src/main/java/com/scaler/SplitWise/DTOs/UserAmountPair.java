package com.scaler.SplitWise.DTOs;

import com.scaler.SplitWise.Model.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserAmountPair {
    private double amount;
    private User user;
    public UserAmountPair(User user, double amount) {
        this.amount = amount;
        this.user = user;
    }

    public UserAmountPair() {
    }
}
