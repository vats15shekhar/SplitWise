package com.scaler.SplitWise.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Expense extends BaseModel{

    private double amount;
    private String description;

    @ManyToOne
    private User createdBy; // One user can create many expenses -> Many to One -> User to Expense

    @ManyToOne
    private Currency baseCurrency; // One currency can have many expenses

    @OneToMany
    private List<User> participants; // One User can have many expenses, one expense can have many users

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

}
