package com.scaler.SplitWise.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@Entity
public class ExpensePayingUser extends User {

    @ManyToOne
    private User user;

    @ManyToOne
    private Expense expense;

    private double amount;


}
