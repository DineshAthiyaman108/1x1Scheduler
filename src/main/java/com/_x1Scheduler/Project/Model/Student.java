package com._x1Scheduler.Project.Model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
/**
 * Represents a Student entity in the system.
 * This class maps to the 'student' table in the database.
 */
@Data
@Entity
public class Student {

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY)
    @Column(name = "s_id")
    private int studentId;

    @Column(name= "s_email")
    private String studentEmail;

    @Column(name = "s_password")
    private String studentPassword;

    @Column(name = "s_name")
    private String studentName;

    @Column(name ="s_college")
    private String studentCollegeName;

    @Column(name = "s_phnumber")
    private String studentPhoneNumber;

    @Column(name = "s_area_interest")
    private String studentAreaOfInterest;

    @Column(name = "s_premium")
    private boolean StudentPremimum;

    @Column(name = "role")
    private String role;

}
