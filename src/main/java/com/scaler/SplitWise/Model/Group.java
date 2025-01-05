package com.scaler.SplitWise.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Group extends BaseModel{

    private String name;
    private String description;
    @ManyToOne
    private User createdBy; // One user can create many groups, one group can be created by one user.

    @OneToMany
    private List<Expense> expenseList; // One group can have many expenses, but one expense can only belong to one group.

    @ManyToMany
    private List<User> users; // One user can be in many groups, one group can have many users.

    @ManyToMany
    private List<User> admins; // One user can be admin in many groups, one group can have many users as admin



}
