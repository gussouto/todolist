package com.project.todolist.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskDTO {

    private String task;
    private String description;
    private LocalDate date;
    private boolean completed;
}
