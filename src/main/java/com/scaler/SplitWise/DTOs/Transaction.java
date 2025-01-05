package com.scaler.SplitWise.DTOs;

import com.scaler.SplitWise.Model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {

    private User from;
    private User to;
    private Double amount;

    public Transaction() {}
    public Transaction(User from, User to, Double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }
}
