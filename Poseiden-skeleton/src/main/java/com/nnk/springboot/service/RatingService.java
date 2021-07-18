package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class RatingService {

    /**
     * @see RatingRepository
     */
    @Autowired
    private RatingRepository ratingRepository;


    /**
     * Find all rating.
     *
     * @return the rating list
     */
    public List<Rating> findAll() {
        log.info("---------------> find all ratings" );
        return ratingRepository.findAll();
    }


    /**
     * Find rating by id.
     *
     * @param id the rating id
     * @return the rating
     */
    public Rating findById(Integer id) {
        log.info("---------------> find rating by id " + id );
        return ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
    }


    /**
     * Save rating.
     *
     * @param rating the rating to save
     */
    public void save(Rating rating) {
        log.info("---------------> save rating" );
        ratingRepository.save(rating);
    }


    /**
     * Delete rating by id.
     *
     * @param id the rating id to delete
     */
    public void delete(Integer id) {
        log.info("---------------> delete rating by id " +  id );
        ratingRepository.deleteById(id);
    }
}
