package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest

public class RatingServiceTest {

    @Autowired
    private RatingService ratingService;

    @Test
    public void ratingTest() {

        Rating rating = new Rating("aaaa", "bbbb", "cccc", 10);

        // Save
        ratingService.save(rating);
        rating = ratingService.findById(rating.getId());
        Assert.assertNotNull(rating.getId());
        Assert.assertTrue(rating.getOrderNumber() == 10);


        // Update
        rating.setOrderNumber(20);
        ratingService.save(rating);
        rating = ratingService.findById(rating.getId());
        Assert.assertEquals(20, rating.getOrderNumber().intValue());

        // Find
        List<Rating> listResult = ratingService.findAll();
        Assert.assertTrue(listResult.size() > 0);

        // Delete
        Integer id = rating.getId();
        ratingService.delete(id);
        assertThrows(IllegalArgumentException.class, () -> ratingService.findById(id));
    }
}
