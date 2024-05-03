package org.example.clase6gtics.entity;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "jobs")
public class Jobs {

    @Id
    @Column(name = "job_id")
    private String jobid;
    @Column(name = "job_title",nullable = false)
    private String jobtitle;
    @Column(name = "min_salary")
    private int minsalary;
    @Column(name = "max_salary")
    private int maxsalary;

}