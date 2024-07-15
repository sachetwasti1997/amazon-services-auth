package com.sachet.amazon_services_auth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "user",
        indexes = {@Index(name = "idx_name_email", columnList="email")})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private String countryCode;
    private String phone;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Address> addresses;

    public void addUser(List<Address> addresses) {
        this.setAddresses(addresses);
        addresses.forEach(address -> address.setUser(this));
    }
}
