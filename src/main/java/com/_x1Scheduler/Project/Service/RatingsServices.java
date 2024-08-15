package com._x1Scheduler.Project.Service;


import com._x1Scheduler.Project.Model.Ratings;
import com._x1Scheduler.Project.Repository.RatingsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Ratings-related operations.
 */

@Service
public class RatingsServices
{
    @Autowired
    private RatingsDao ratingsDao;

    //Saves the provided Ratings object to the database.
    public String setRatings(Ratings ratings)
    {
        Ratings savedRatings =  ratingsDao.save(ratings);
        if (savedRatings != null)
            return "Rating Saved";
        return  "Rating Not Saved";
    }
// Retrieves a list of all Ratings from the database.
    public List<Ratings> getAllRating()
    {
         return ratingsDao.findAll();

    }

}
