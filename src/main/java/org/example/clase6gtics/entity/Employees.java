package org.example.clase6gtics.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employee_id;

    @Column(name = "first_name")
    @NotBlank
    private String firstName;

    @Column(name = "last_name")
    @NotBlank
    private String lastName;
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @Size(max=8, message = "Debe tener un mínimo de 8 carácteres")
    @NotBlank(message = "No debe ser vacío o blanco")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "hire_date")
    private Date hireDate;
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Jobs job;

    @Column(name = "salary")
    @Positive
    private Float salary;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employees manager;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Departments department;
    @Column(name = "enabled")
    private boolean enable;

}