package com._x1Scheduler.Project.Repository;

import com._x1Scheduler.Project.Model.Scheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerDao extends JpaRepository<Scheduler,Integer>
{
public Scheduler findByMentorId(int id);
}
