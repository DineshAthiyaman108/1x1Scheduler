package com._x1Scheduler.Project.Repository;

import com._x1Scheduler.Project.Model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorDao extends JpaRepository<Mentor,Integer>
{
    Mentor findByMentorEmail(String mentorEmail);

    @Query("select m from Mentor m where m.mentorIndustrailRole like %:mentorRole%")
    List<Mentor> findByMentorRole(@Param("mentorRole") String mentorRole);


}
