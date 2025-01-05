package com.scaler.SplitWise.Service.SettleUp.Strategies;

import com.scaler.SplitWise.DTOs.Transaction;
import com.scaler.SplitWise.DTOs.UserAmountPair;
import com.scaler.SplitWise.Model.ExpenseOwingUser;
import com.scaler.SplitWise.Model.ExpensePayingUser;
import com.scaler.SplitWise.Model.User;

import java.util.*;

public class MinMaxSettleUpStrategy implements SettleUpTransactionCalculatorStrategy{
    @Override
    public List<Transaction> getTransactions(List<ExpenseOwingUser> expenseOwingUsers, List<ExpensePayingUser> expensePayingUsers) {

        List<Transaction> transactions = new ArrayList<Transaction>();
        HashMap<User, Double> debtCollectionMap = new HashMap<User, Double>();

        //Calculating the total borrowed or lent amount for each user. Map will store the same.
        //update the owing users with negative value

        for(ExpenseOwingUser expenseOwingUser : expenseOwingUsers) {
           // We are marking all the amounts of Users who owe money with a negative sign.
            // That is to indicate that this user owes money to other people.
            if (! debtCollectionMap.containsKey(expenseOwingUser.getUser()))
            {
                debtCollectionMap.put(expenseOwingUser.getUser(), -1 * expenseOwingUser.getAmount());
            }

            // From a list of expenses, calculate the total amount owed by a particular participant
            else
            {
                double currentAmount = debtCollectionMap.get(expenseOwingUser.getUser());
                // We are doing currentAmount - expenseOwingUser.getAmount(), because the current amount is
                // negative. We do - expenseOwingUser.getAmount() which is nothing but
                // currentAmount + (-1 * expenseOwingUser.getAmount()). This will add the negative amounts
                // leading to a total negative number against each user. That will indicate the total
                // amount owed by that user
                double updatedAmount = currentAmount - expenseOwingUser.getAmount();

                debtCollectionMap.put(expenseOwingUser.getUser(), updatedAmount);

            }

        }

        //Calculating the total borrowed or lent amount for each user. Map will store the same.
        //update the users who paid users with positive value

        for(ExpensePayingUser expensePayingUser : expensePayingUsers) {
            if (! debtCollectionMap.containsKey(expensePayingUser.getUser()))
            {
                debtCollectionMap.put(expensePayingUser.getUser(),  expensePayingUser.getAmount());
            }
            else
            {
                // The users who paid will hve an amount indicated with a positive sign
                double currentAmount = debtCollectionMap.get(expensePayingUser.getUser());
                double updatedAmount = currentAmount + expensePayingUser.getAmount();

                debtCollectionMap.put(expensePayingUser.getUser(), updatedAmount);
            }

        }

        /*
        Comparator.comparingDouble(UserAmountPair::getAmount)
        This defines the comparison logic for the heap.

        UserAmountPair::getAmount: A method reference that points to the getAmount
        method in the UserAmountPair class, which presumably returns a double.

        Comparator.comparingDouble: Creates a comparator to compare UserAmountPair
        instances based on the amount field.
         */

        //Start getting the transactions
        //Creating two buckets -> borrowers (minheap) and lendors(maxHeap)
        //minHeap => borrowers
        PriorityQueue<UserAmountPair> borrowersMinHeap = new PriorityQueue<>(Comparator.comparingDouble(UserAmountPair::getAmount));
        //maxHeap => lendors
        PriorityQueue<UserAmountPair> lendorsMaxHeap = new PriorityQueue<>(Comparator.comparingDouble(UserAmountPair::getAmount).reversed());

        // Iterate on debtCollectionMap. Add all the negative values to the  min heap
        // All the positive values to the max heap
        for(Map.Entry<User,Double> userAmount : debtCollectionMap.entrySet()){
            if(userAmount.getValue() < 0){
                borrowersMinHeap.add(new UserAmountPair(userAmount.getKey(), userAmount.getValue()));
            } else if(userAmount.getValue() > 0){
                lendorsMaxHeap.add(new UserAmountPair(userAmount.getKey(), userAmount.getValue()));
            }
        }

        while(!borrowersMinHeap.isEmpty()){
            UserAmountPair maxBorrower = borrowersMinHeap.poll();
            UserAmountPair maxLendor = lendorsMaxHeap.poll();
            if(Math.abs(maxBorrower.getAmount()) > maxLendor.getAmount()){
                //if borrower's amount is greater than lendor, then we clear up the lendor
                maxBorrower.setAmount(maxBorrower.getAmount() + maxLendor.getAmount());
                borrowersMinHeap.add(maxBorrower);
                Transaction t = new Transaction(maxBorrower.getUser(), maxLendor.getUser(), maxLendor.getAmount());
                transactions.add(t);
            } else if(Math.abs(maxBorrower.getAmount()) < maxLendor.getAmount()){
                //if lendor amount is greater than borrower, then we clear up the borrower
                maxLendor.setAmount(maxLendor.getAmount() + maxBorrower.getAmount());
                lendorsMaxHeap.add(maxLendor);
                Transaction t = new Transaction(maxBorrower.getUser(), maxLendor.getUser(), Math.abs(maxBorrower.getAmount()));
                transactions.add(t);
            } else {
                Transaction t = new Transaction(maxBorrower.getUser(), maxLendor.getUser(), maxLendor.getAmount());
                transactions.add(t);
            }
        }

        return transactions;

    }
}
