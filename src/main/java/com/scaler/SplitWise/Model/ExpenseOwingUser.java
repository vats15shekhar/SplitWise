package com.scaler.SplitWise.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class ExpenseOwingUser extends User{

    @ManyToOne
    private User user;

    @ManyToOne
    private Expense expense;

    private double amount;
}
