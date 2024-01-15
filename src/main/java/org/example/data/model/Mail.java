package org.example.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;
import org.example.util.MailType;

import java.time.LocalDate;
@Data
@Entity
public class  Mail {
    @Id
    private Long id;
    private String title;
    private String message;
    private LocalDate dateCreated = LocalDate.now();
    @Enumerated
    private MailType mailType;
}
