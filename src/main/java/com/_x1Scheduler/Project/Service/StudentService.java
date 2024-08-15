package com._x1Scheduler.Project.Service;

import com._x1Scheduler.Project.Model.Scheduler;
import com._x1Scheduler.Project.Model.Student;
import com._x1Scheduler.Project.Repository.StudentDao;
import org.apache.catalina.valves.StuckThreadDetectionValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
/**
 * Service class for managing student-related operations.
 */
@Service
public class StudentService
{

    @Autowired
    private StudentDao studentDao;

    //Signs up a new student and saves their details.
    public Student signUpStudentDetails(Student student)
    {
        Student saveStudentDetail = studentDao.save(student);
        return saveStudentDetail;
    }

    //Signs in a student by validating their email and password.
    public boolean studentSignIn(String email, String password)
    {
        Student studentData = studentDao.findByStudentEmail(email);
        if (studentData != null)
        {
            if(studentData.getStudentEmail().equals(email) && studentData.getStudentPassword().equals(password))
            {
                return true;
            }
        }
        return  false;
    }
//Retrieves a student's details by their ID.
    public Student getStudnetById(int id)
    {
        Optional<Student> student = studentDao.findById(id);
        if (student.isPresent())
        {
            return student.get();
        }
        return null;
    }
    //Checks if a student has a premium account based on their ID
    public boolean checkPremiem(Scheduler scheduler)
    {


        Optional<Student> getById = studentDao.findById(scheduler.getStudentId());
        if (getById.isPresent())
        {
            return getById.get().isStudentPremimum();
        }
        return  false;
    }
}
