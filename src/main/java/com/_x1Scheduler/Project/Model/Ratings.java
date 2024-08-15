package com._x1Scheduler.Project.Model;


import jakarta.persistence.*;
import lombok.Data;

import javax.print.attribute.standard.MediaSize;
/**
 * Represents a Ratings entity in the system.
 * This class maps to the 'ratings' table in the database.
 */
@Data
@Entity
public class Ratings
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    private int ratingsId;

    @Column(name = "s_id")
    private int scheduleId;

    @Column(name = "s_name")
    private String userName;

    @Column(name = "m_name")
    private String MentorName;

    @Column(name = "ratings")
    private int Ratings;

    @Column(name = "Comments")
    private String Comments;

}
