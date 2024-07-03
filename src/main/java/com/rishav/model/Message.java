package com.rishav.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String content;

    private LocalDateTime cratedAt;

    @ManyToOne
    private Chat chat;

    @ManyToOne
    private User sender;

}
