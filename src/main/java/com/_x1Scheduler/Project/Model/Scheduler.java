package com._x1Scheduler.Project.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
/**
 * Represents a Scheduler entity in the system.
 * This class maps to the 'scheduler' table in the database.
 */
@Entity
@Data
@ToString
public class Scheduler
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int schedulId;

    @Column(name = "s_name")
    private String studentName;

    @Column(name = "m_id")
    private int mentorId;

    @Column(name = "m_name")
    private String mentorName;

    @Column(name = "s_id")
    private int studentId;

    @Column(name = "s_email")
    private String studentEmail;

    @Column(name = "sch_date")
    private String date;

    @Column(name = "sch_time")
    private String time;

    @Column(name = "sel_m_id")
    private boolean selectedMentorId;

    @Column(name = "sch_pay")
    private int pay;

    @Column(name = "sc_course")
    private String selectedCourse;

    @Column(name = "m_email")
    private String mentorEmail;

    @Column(name = "end_session")
    private String end_session;

}

