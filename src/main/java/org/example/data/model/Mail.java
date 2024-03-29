package org.example.data.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.util.MailType;

import java.time.LocalDate;
@Data
@Entity
public class  Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDate dateCreated = LocalDate.now();
    @Enumerated
    private MailType mailType;
}
