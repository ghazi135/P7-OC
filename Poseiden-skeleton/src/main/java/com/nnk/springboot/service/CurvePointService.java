package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
@Transactional
public class CurvePointService {

    /**
     * @see CurvePointRepository
     */
    @Autowired
    CurvePointRepository curvePointRepository;


    /**
     * Find all curve point.
     *
     * @return curve point list
     */
    public List<CurvePoint> findAll() {
        log.info("---------------> find all Curve points" );
        return curvePointRepository.findAll();
    }

    /**
     * Save curve point.
     *
     * @param curvePoint the curve point to save
     */

    public void save(CurvePoint curvePoint) {
        log.info("---------------> save curve point" );
        curvePoint.setAsOfDate(Timestamp.valueOf(LocalDateTime.now()));
        curvePoint.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        curvePointRepository.save(curvePoint);
    }

    /**
     * Find curve point by id.
     *
     * @param id the curve point id
     * @return the curve point
     */

    public CurvePoint findById(Integer id) {
        log.info("---------------> find curve point by id " + id);
        return curvePointRepository.findById(id)
                                   .orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
    }

    /**
     * Delete curve point by id.
     *
     * @param id the curve point id to delete
     */
    public void delete(Integer id) {
        log.info("---------------> delete curve point by id" + id  );
        curvePointRepository.deleteById(id);
    }
}
