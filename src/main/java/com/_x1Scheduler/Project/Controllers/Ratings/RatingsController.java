package com._x1Scheduler.Project.Controllers.Ratings;


import com._x1Scheduler.Project.Model.Ratings;
import com._x1Scheduler.Project.Service.RatingsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 * The RatingsContorller handles the http request related to the Ratings
 *
 *
 * Api Endpoints
 * POST /getallrating gets the all Rating
 * POST /saveRatings save the Ratings
 */
@RestController
public class RatingsController
{

    @Autowired
    private RatingsServices ratingsServices;


    @PostMapping("/saveRatings")
    public ResponseEntity<String> saveRatings(@RequestBody Ratings ratings)
    {
        System.out.println(ratings.toString());
        String savedRating =ratingsServices.setRatings(ratings);
        if (savedRating.equals(null))
            return new ResponseEntity<>("Rating Not Saved", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("Rating Saved ",HttpStatus.OK);
    }

    @GetMapping("/getallrating")
    public List<Ratings> getAllRatings()
    {
     return ratingsServices.getAllRating();
    }




}
