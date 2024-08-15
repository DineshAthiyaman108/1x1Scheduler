package com._x1Scheduler.Project.Controllers.Student;


import com._x1Scheduler.Project.Model.Student;
import com._x1Scheduler.Project.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * The StudentContorller handles the http request related to the Student
 *
 *
 * Api Endpoints
 * POST /api/Student/signup for a New Student
 * POST /api/Student/signin for Student Signin
 */
@RestController
@RequestMapping("/api/student")
public class StudentController
{
    @Autowired
    private StudentService studentService;

    private  boolean login;

    @PostMapping("/signup")
    public ResponseEntity<String> saveStudentDetails(@RequestBody Student student)
    {
        if(student != null)
        {
            Student  savedStudent = studentService.signUpStudentDetails(student);
            if(savedStudent != null)
                return  new ResponseEntity<>("successfulled SignedUp" , HttpStatus.OK);
        }
        return new ResponseEntity<>("not Successfully SignUp",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> studentLogin(@RequestBody Student student)
    {
        if(student.getStudentEmail() != null && student.getStudentPassword() != null)
        {
            login = studentService.studentSignIn(student.getStudentEmail(),student.getStudentPassword());
            if (login)
                return  new ResponseEntity<>("Login",HttpStatus.OK);
        }
        return  new ResponseEntity<>("emil or password is wrong",HttpStatus.BAD_REQUEST);
    }



}
