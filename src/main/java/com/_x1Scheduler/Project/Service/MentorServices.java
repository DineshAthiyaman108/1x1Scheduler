package com._x1Scheduler.Project.Service;

import com._x1Scheduler.Project.Model.Mentor;
import com._x1Scheduler.Project.Repository.MentorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Mentor-related operations.
 */
@Service
public class MentorServices
{
    @Autowired
    private MentorDao mentorDao;


    //Saves the provided Mentor details to the database.
    public  Mentor saveMentorDetail(Mentor mentor)
    {
       Mentor savedDetail = mentorDao.save(mentor);
       return  savedDetail;
    }

    // Validates the Mentor's email and password.
    public boolean findMentorByEmail(String mentorEmail , String mentorPassword)
    {
      Mentor mentorData = mentorDao. findByMentorEmail(mentorEmail);
      if(mentorData.getMentorEmail().equals(mentorEmail) && mentorData.getMentorPassword().equals(mentorPassword))
          return true;

      return false;
    }

    //Retrieves a list of Mentors based on the selected course.
    public List<Mentor> getMentorByCourse(String selectedCourse)
    {

        if (selectedCourse != null)
        {
          return   mentorDao.findByMentorRole(selectedCourse);
        }


         return null;
    }

    // Retrieves a Mentor by their ID.
    public Mentor getMentorBtId(int id)
    {
       Optional<Mentor> mentor = mentorDao.findById(id);
       if (mentor.isPresent())
       {
           return mentor.get();
       }
       return null;
    }


}
