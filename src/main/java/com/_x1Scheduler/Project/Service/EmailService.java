package com._x1Scheduler.Project.Service;

import com._x1Scheduler.Project.Model.Scheduler;
import com._x1Scheduler.Project.Model.Student;
import org.hibernate.boot.archive.scan.internal.ScanResultImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for sending emails related to scheduling.
 */
@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text, String email) {
    
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendEmailToMentor(Scheduler bookedSchedular) {
        String subject = "New Student Assignment for Course: " + bookedSchedular.getSelectedCourse() + "The Student User Name is " + bookedSchedular.getStudentName();
        String text = "Dear " + bookedSchedular.getMentorName() + ",\n\n" +
                "You have been assigned a new student for the course: " + bookedSchedular.getSelectedCourse() + ".\n" +
                "Please log in to your mentor dashboard to review the student's details.\n\n" +
                "Best regards,\nYour Scheduler Team";
        sendSimpleMessage(bookedSchedular.getMentorEmail(), subject, text, "dineshathiyaman810@gmail.com");
    }

    public void sendEmailToStudent(Scheduler bookedSchedular) {
        String subject = "Mentor Assigned for Your Course: " + bookedSchedular.getSelectedCourse();
        String text = "Dear " + bookedSchedular.getStudentName() + ",\n\n" +
                "We are pleased to inform you that " + bookedSchedular.getMentorName() + " has been assigned as your mentor for the course: " + bookedSchedular.getSelectedCourse() + ".\n" +
                "You can contact your mentor via the platform or directly through their email.\n\n" +
                "Best regards,\nYour Scheduler Team";
        sendSimpleMessage(bookedSchedular.getStudentEmail(), subject, text, "dineshathiyaman810@gmail.com");
    }

    public void sendCancellationEmailToUser(String cancledBy, String name, String studentEmail, String MentorEmail, String course) {
        String subject = "Class Cancellation: ";
        String text = "Dear " + name + ",\n\n" +
                "We regret to inform you that the class for your course: " + course +
                " has been cancelled by " + cancledBy + "\n" +
                "We apologize for any inconvenience this may cause.\n\n" +
                "Best regards,\nYour Scheduler Team";
        sendSimpleMessage(studentEmail, subject, text, "dineshathiyaman810@gmail.com");
    }


}
