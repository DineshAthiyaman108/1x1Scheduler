package com._x1Scheduler.Project.Model;

import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Data;
import lombok.Generated;
import lombok.ToString;

import java.util.Date;
import java.util.List;
/**
 * Represents a Mentor entity in the system.
 * This class maps to the 'mentor' table in the database.
 */

@Data
@Entity
@ToString
public class Mentor
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_Id")
    private int id;

    @Column(name = "m_name")
    private String metorName;

    @Column(name ="m_Email")
    private String mentorEmail;

    @Column(name = "m_Password")
    private String mentorPassword;

    @Column(name = "m_role")
    private String mentorIndustrailRole;

    @Column(name = "m_experience")
    private int mentorExperience;

    @Column(name = "m_company")
    private String mentorCompany;

    @Column(name =  "m_number")
    private String mentorNumber;

    @Column(name = "m_avaliable")
    private boolean mentorAvaliable;

    @Column(name ="m_date")
    private String mentorDate;

    @Column(name ="m_time")
    private String time;



}
