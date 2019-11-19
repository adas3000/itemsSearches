package com.promo.promotions.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class _User {

    @Id
    private Long id;
    @Column
    private String email;
    @JsonIgnore
    private String password;





}
