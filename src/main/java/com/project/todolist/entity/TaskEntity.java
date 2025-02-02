package com.project.todolist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tasks")
public class TaskEntity implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String task;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private boolean completed;

    // Constructor sem id para usar na TaskDTO
    public TaskEntity(String task, String description, LocalDate date, boolean completed) {
        this.task = task;
        this.description = description;
        this.date = date;
        this.completed = completed;
    }
}

