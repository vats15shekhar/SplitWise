package com.scaler.SplitWise.Service.SettleUp.Strategies;

import com.scaler.SplitWise.DTOs.Transaction;
import com.scaler.SplitWise.Model.ExpenseOwingUser;
import com.scaler.SplitWise.Model.ExpensePayingUser;

import java.util.List;

public class GiveToNextSettleUpStrategy implements SettleUpTransactionCalculatorStrategy{
    @Override
    public List<Transaction> getTransactions(List<ExpenseOwingUser> expenseOwingUsers, List<ExpensePayingUser> expensePayingUser) {
        return List.of();
    }
}
