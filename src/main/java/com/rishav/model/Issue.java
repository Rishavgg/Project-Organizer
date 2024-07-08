package com.rishav.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comments;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User assignee;

    @JsonIgnore
    @ManyToOne
    private Project project;

    private String title;
    private String description;
    private String status;
    private Long projectId;
    private String priority;
    private LocalDate dueDate;
    private List<String> tags = new ArrayList<>();
    // jsonIgnore = This will avoid recursion problem because when we call our frontend this comments will call issue
    // and issue will call comments and goes on.
    // mappedBy = does not create the separate table for comments and cascade = whenever we edit the comment it will
    // be automatically reflected here;
    @JsonIgnore
    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

}
