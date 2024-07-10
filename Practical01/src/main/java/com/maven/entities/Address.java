package com.maven.entities;

import lombok.*;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import javax.persistence.*;

@Component
@Data
@Table(name = "userAddress")
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    @Column(length = 20)
    private String userCity;
    @Column(length = 20)
    private String userState;
    @Column(length = 20)
    private String userCountry;
    @Column(length = 20)
    private String userPincode;
    private String profileImage;
    @OneToOne
    @ToString.Exclude
    private User user;

}