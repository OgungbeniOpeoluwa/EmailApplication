package org.example.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Data
@Entity
public class User {
    @Id
    private Long id;
    private String name;
    private String age;
    private String phoneNumber;
    private String password;

}
