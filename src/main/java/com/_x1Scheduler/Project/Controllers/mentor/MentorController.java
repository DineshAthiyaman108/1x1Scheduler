package com._x1Scheduler.Project.Controllers.mentor;

import com._x1Scheduler.Project.Model.Mentor;
import com._x1Scheduler.Project.Model.Student;
import com._x1Scheduler.Project.Service.MentorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * The MentorContorller handles the http request related to the mentor
 *
 *
 * Api Endpoints
 * POST /api/mentor/signup for a New Mentor
 * POST /api/mentor/signin for Mentor Signin
 */


@RestController
@RequestMapping("/api/mentor")
public class MentorController
{
    private  boolean login;

    @Autowired
    private MentorServices mentorServices;

    @PostMapping("/signup")
    public ResponseEntity<String> saveMentorDetails(@RequestBody Mentor mentor)
    {
        System.out.println(mentor + "in post mapping");
         if(mentor != null)
         {
          Mentor savedMentorDetail =   mentorServices.saveMentorDetail(mentor);
          if(savedMentorDetail != null)
              return  new ResponseEntity<>("successfully SignUp" , HttpStatus.OK);
         }
        return  new ResponseEntity<>("not successfully SignUp",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> MentorLogin(@RequestBody Mentor mentor)
    {
        if(mentor.getMentorEmail() != null && mentor.getMentorPassword() != null)
        {
           login = mentorServices.findMentorByEmail(mentor.getMentorEmail(),mentor.getMentorPassword());
            if (login)
                return  new ResponseEntity<>("Login",HttpStatus.OK);
        }
        return  new ResponseEntity<>("email or password is wrong",HttpStatus.BAD_REQUEST);
    }
}
