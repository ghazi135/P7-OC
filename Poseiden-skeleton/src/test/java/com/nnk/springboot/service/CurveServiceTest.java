package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurveServiceTest {

    @Autowired
    private CurvePointService curvePointService;

    @Test
    public void curvePointTest() {

        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(10);
        curvePoint.setTerm(10d);
        curvePoint.setValue(30d);
        curvePoint.setAsOfDate(Timestamp.valueOf(LocalDateTime.now()));
        curvePoint.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));


        // Save
        curvePointService.save(curvePoint);
        curvePoint = curvePointService.findById(curvePoint.getId());
        Assert.assertNotNull(curvePoint.getId());

        Assert.assertTrue(curvePoint.getCurveId() == 10);

        // Update
        curvePoint.setCurveId(20);
        curvePointService.save(curvePoint);
        curvePoint = curvePointService.findById(curvePoint.getId());
        Assert.assertEquals(20, curvePoint.getCurveId().intValue());

        // Find
        List<CurvePoint> listResult = curvePointService.findAll();
        Assert.assertTrue(listResult.size() > 0);

        // Delete
        Integer id = curvePoint.getId();
        curvePointService.delete(id);
        assertThrows(IllegalArgumentException.class, () -> curvePointService.findById(id));
    }


}
