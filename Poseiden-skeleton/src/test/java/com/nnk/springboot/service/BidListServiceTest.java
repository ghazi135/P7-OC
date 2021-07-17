package com.nnk.springboot.service;


import com.nnk.springboot.domain.BidList;
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
public class BidListServiceTest {
    @Autowired
    private BidListService bidListService;

    @Test
    public void bidListTest() {

        BidList bid = new BidList();
        bid.setAccount("AAA");
        bid.setType("BBB");
        bid.setBidQuantity(10d);

        // Save
        bidListService.save(bid);
        bid = bidListService.findById(bid.getBidListId());
        Assert.assertNotNull(bid.getBidListId());
        Assert.assertEquals(bid.getBidQuantity(), 10d, 10d);

        // Update
        bid.setBidQuantity(20d);
        bidListService.save(bid);
        bid = bidListService.findById(bid.getBidListId());
        Assert.assertEquals(bid.getBidQuantity(), 20d, 20d);

        // Find
        List<BidList> listResult = bidListService.findAll();
        Assert.assertTrue(listResult.size() > 0);

        // Delete
        Integer id = bid.getBidListId();
        bidListService.delete(id);
        assertThrows(IllegalArgumentException.class , () -> bidListService.findById(id));
    }
}
