package com.nnk.springboot.repository;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurvePointRepositoryTests {

    @Autowired
    private CurvePointRepository curvePointRepository;

    @Test
    public void curvePointTest() {

        CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
        curvePoint.setAsOfDate(Timestamp.valueOf(LocalDateTime.now()));
        curvePoint.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));


        // Save
        curvePoint = curvePointRepository.save(curvePoint);
        Assert.assertNotNull(curvePoint.getId());
        Assert.assertTrue(curvePoint.getCurveId() == 10);

        // Update
        curvePoint.setCurveId(20);
        curvePoint = curvePointRepository.save(curvePoint);
        Assert.assertTrue(curvePoint.getCurveId() == 20);

        // Find
        List<CurvePoint> listResult = curvePointRepository.findAll();
        Assert.assertTrue(listResult.size() > 0);

        // Delete
        Integer id = curvePoint.getId();
        curvePointRepository.delete(curvePoint);
        Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
        Assert.assertFalse(curvePointList.isPresent());
    }

}
