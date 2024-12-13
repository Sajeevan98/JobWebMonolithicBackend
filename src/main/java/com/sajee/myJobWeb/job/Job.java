package com.sajee.myJobWeb.job;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sajee.myJobWeb.company.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;

    @JsonIgnore
    @ManyToOne
    private Company company;


}
