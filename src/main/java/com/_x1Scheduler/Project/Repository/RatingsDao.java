package com._x1Scheduler.Project.Repository;

import com._x1Scheduler.Project.Model.Ratings;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingsDao extends JpaRepository<Ratings, Integer> {
}
