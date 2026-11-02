package com.substring.helpdesk.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "help_desk_tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String summary;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(unique = true)
    private String email;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    @Enumerated(EnumType.STRING)
    private Status status;

    @PrePersist
    void preSave(){
        if(this.createdOn == null){
            this.createdOn = LocalDateTime.now();
        }
        this.createdOn = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate(){
        if(this.updatedOn == null){
            this.updatedOn = LocalDateTime.now();
        }
        this.updatedOn = LocalDateTime.now();
    }

}
