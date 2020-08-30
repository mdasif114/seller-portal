package com.seller.portal.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.*;

@Setter@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long userId;

    private String accountType;
    private String shopBasedIn;

    @Column(unique = true)
    private String mobileNumber;

    @Column(unique = true)
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Role> roles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Bank bank;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Identity identity;

    public User(String accountType, String shopBasedIn, String email, String mobileNumber, String password) {
        this.accountType = accountType;
        this.shopBasedIn = shopBasedIn;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.password = password;
    }

    public User(Long userId) {
        this.userId = userId;
    }
}