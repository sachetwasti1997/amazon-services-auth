package com.sachet.amazon_services_auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String lineOne;
    private String lineTwo;
    private int postalCode;
    private AddressType addressType;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", lineOne='" + lineOne + '\'' +
                ", lineTwo='" + lineTwo + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }
}
