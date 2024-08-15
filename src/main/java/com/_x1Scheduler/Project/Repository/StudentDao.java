package com._x1Scheduler.Project.Repository;

import com._x1Scheduler.Project.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student , Integer>
{
    Student findByStudentEmail(String studentEmail);
}
