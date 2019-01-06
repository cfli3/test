package com.example.demo.domain;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Getter
public class User {

    private @Id @GeneratedValue Long id;

    @NotNull
    private Integer userId;

    private String loginName;

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    public User() {
    }

    public User(Integer userId, String userLogin, String firstName, String lastName, String address, String email){
        this.userId = userId;
        this.loginName = userLogin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
    }

}
