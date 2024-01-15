package org.example.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class EmailApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String domainName;
    @OneToMany
    private List <Mail> mail;
    private boolean isLogIn = false;
    private Long userId;
}
