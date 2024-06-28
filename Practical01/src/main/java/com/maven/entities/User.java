package com.maven.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Component
@Table(name = "register")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20)
    private String userName;
    private String email;
    @Column(length = 20)
    private String contact;
    @Column(length = 20)
    private String password;
}
