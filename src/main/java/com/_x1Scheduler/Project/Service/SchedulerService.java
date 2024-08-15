package com._x1Scheduler.Project.Service;

import com._x1Scheduler.Project.Model.Mentor;
import com._x1Scheduler.Project.Model.Scheduler;
import com._x1Scheduler.Project.Repository.SchedulerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Optional;
/**
 * Service class for managing scheduling-related operations.
 */

@Service
public class SchedulerService {

    private boolean cancle;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SchedulerDao schedularDao;

    @Autowired
    private MentorServices mentorServices;

    @Autowired
    private EmailService emailService;
//Schedules a class with an available mentor.
    public Scheduler scheduleClass(List<Mentor> mentorSelectBycourse, Scheduler scheduler) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        for (Mentor mentor : mentorSelectBycourse) {
            if (mentor.isMentorAvaliable()) {

                if (mentor.getMentorDate().contains(scheduler.getDate())) {

                    scheduler.setMentorId(mentor.getId());
                    if (scheduler.getTime().equals("30"))
                        scheduler.setPay(2000);
                    else if (scheduler.getTime().equals("45"))
                        scheduler.setPay(3000);
                    else
                        scheduler.setPay(4000);
                    mentor.setMentorAvaliable(false);
                    LocalTime time = LocalTime.parse(mentor.getTime().substring(0, 6).trim(), formatter);
                    LocalTime updatedTime = time.plusMinutes(Integer.parseInt(scheduler.getTime()));
                    System.out.println(updatedTime.toString() + " The String");
                    String updatedTimeString = updatedTime.toString();
                    scheduler.setTime(mentor.getTime().substring(0, 6));
                    scheduler.setEnd_session(updatedTimeString);
                    mentorServices.saveMentorDetail(mentor);
                    scheduler.setMentorId(mentor.getId());
                    scheduler.setMentorName(mentor.getMetorName());
                    scheduler.setMentorEmail(mentor.getMentorEmail());
                    schedularDao.save(scheduler);
                    return scheduler;
                }

            }
        }
        return null;
    }
 //Cancels a scheduled class and sends a cancellation email.
    public Scheduler cancleScheduled(Scheduler cancleScheduledClass) {
        Optional<Scheduler> cancleSchesuled = schedularDao.findById(cancleScheduledClass.getSchedulId());
        schedularDao.deleteById(cancleScheduledClass.getSchedulId());
        cancle = schedularDao.existsById(cancleScheduledClass.getSchedulId());
        if (cancle) {
            return null;
        }
        Mentor mentor = mentorServices.getMentorBtId(cancleSchesuled.get().getMentorId());
        mentor.setMentorAvaliable(true);
        if (cancleScheduledClass.getMentorId() != 0) {

            emailService.sendCancellationEmailToUser
                    (mentor.getMetorName()
                            , cancleSchesuled.get().getStudentName()
                            , cancleSchesuled.get().getMentorName()
                            , cancleScheduledClass.getMentorEmail()
                            , cancleSchesuled.get().getSelectedCourse());

            mentorServices.saveMentorDetail(mentor);
            return cancleSchesuled.get();
        } else {
            emailService.sendCancellationEmailToUser
                    (

                            cancleScheduledClass.getStudentName(),
                            cancleSchesuled.get().getStudentName()
                            , cancleSchesuled.get().getStudentEmail()
                            , cancleScheduledClass.getMentorEmail()
                            , cancleSchesuled.get().getSelectedCourse());

            return cancleSchesuled.get();

        }

    }
       //Scheduling the class with selected mentor
        public Scheduler scheduler  (Scheduler scheduler)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

            Mentor mentor = mentorServices.getMentorBtId(scheduler.getMentorId());
            Scheduler scheduledClass = schedularDao.findByMentorId(mentor.getId());

            if (!mentor.isMentorAvaliable() || mentor.isMentorAvaliable()) {

                scheduler.setMentorName(mentor.getMetorName());
                scheduler.setMentorId(mentor.getId());
                if (scheduler.getTime().equals("30"))
                    scheduler.setPay(2000);
                else if (scheduler.getTime().equals("45"))
                    scheduler.setPay(3000);
                else
                    scheduler.setPay(4000);
                scheduler.setPay((scheduler.getPay()+2000));
                LocalTime time = LocalTime.parse(scheduledClass.getEnd_session().substring(0,5).trim(), formatter);
                LocalTime updatedTime = time.plusMinutes(Integer.parseInt(scheduler.getTime()));
                String updatedTimeString = updatedTime.toString();
                scheduler.setTime(time.toString());
                scheduler.setEnd_session(updatedTimeString);
                scheduler.setStudentEmail(scheduler.getStudentEmail());
                scheduler.setMentorName(mentor.getMetorName());
                scheduler.setMentorEmail(mentor.getMentorEmail());
                schedularDao.save(scheduler);
                return  scheduler;

            }
            return null;
        }

}
