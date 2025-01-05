package com.scaler.SplitWise.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseModel{

    private String name;
    private String hashedPassword;
    private String email;
    private long mobileNum;

    @ManyToMany
    private List<Group> groupList;

    @Override
    public String toString() {
        return "User{" +
                "username='" + name + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", phoneNumber='" + mobileNum + '\'' +
                '}';
    }

}
