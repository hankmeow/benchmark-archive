package com.hankmew.benchmark.spring.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "account")
public class AccountEntity {
    @Id
    private Long id;
    private String name;
    @Column(name = "created_time")
    private Long createdTime;
    @Column(name = "updated_time")
    private Long updatedTime;
}
