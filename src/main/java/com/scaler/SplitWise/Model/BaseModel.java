package com.scaler.SplitWise.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Setter
@Getter
@MappedSuperclass
@Entity
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /*
    Created Date and modified dates are audit columns. To know when the record
    was created and it was modified. We should not have to worry about the audit columns
    So Spring takes of that part with the annotations @CreatedDate and
    @LastModifiedDate
     */
    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date modifiedDate;

}
