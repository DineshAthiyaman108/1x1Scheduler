package com._x1Scheduler.Project.Controllers.Scheduler;


import com._x1Scheduler.Project.Model.Mentor;
import com._x1Scheduler.Project.Model.Scheduler;
import com._x1Scheduler.Project.Model.Student;
import com._x1Scheduler.Project.Service.EmailService;
import com._x1Scheduler.Project.Service.MentorServices;
import com._x1Scheduler.Project.Service.SchedulerService;
import com._x1Scheduler.Project.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * The StudentContorller handles the http request related to the Scheduling Class
 *
 *
 * Api Endpoints
 * POST /api/Scheduler/newschedule for Scheduling the New Class
 * POST /api/Scheduler/cancle for Cancling the ScheduledClass
 * POST /api/Scheduler/selectbymentor for Scheduling the class by mentor
 */
@RestController
@RequestMapping("/api/scheduler")
public class SchedulerController
{
    @Autowired
    private StudentService studentService;

    @Autowired
    private MentorServices mentorServices;

    @Autowired
    private SchedulerService schedulerService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/newschedule")
    public ResponseEntity<Scheduler> schedulerClass(@RequestBody Scheduler scheduler)
    {
       List<Mentor>  selecetedMentorByCourse = mentorServices.getMentorByCourse(scheduler.getSelectedCourse());
       if (selecetedMentorByCourse!= null)
       {
            Scheduler bookedScheduled =schedulerService.scheduleClass(selecetedMentorByCourse , scheduler);

            if (bookedScheduled != null)
            {

               emailService.sendEmailToStudent(bookedScheduled);
               return new ResponseEntity<>(bookedScheduled, HttpStatus.OK);
            }

       }
       return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }


    @PostMapping("/cancle")
    public  ResponseEntity<String> cancleSchehuled(@RequestBody Scheduler scheduler)
    {
        Scheduler cancleClass =  schedulerService.cancleScheduled(scheduler);
        if (cancleClass != null)
        {
            return new ResponseEntity<>("Cancellation",HttpStatus.OK);
        }
        return new ResponseEntity<>("Cancellation Failed",HttpStatus.OK);
    }

    @PostMapping("/selectbymentor")
    public  ResponseEntity<Scheduler> newSchedulByMentor(@RequestBody Scheduler scheduler)
    {

    boolean premiem =  studentService.checkPremiem(scheduler);

        if(premiem)
        {
          Scheduler scheduled =schedulerService.scheduler(scheduler);
          if (scheduled != null)
              emailService.sendEmailToStudent(scheduled);
          return  new ResponseEntity<>(scheduled , HttpStatus.OK);
        }
 return  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
   }


}
