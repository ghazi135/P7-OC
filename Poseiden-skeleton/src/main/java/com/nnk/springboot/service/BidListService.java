package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
public class BidListService {

    /**
     * @see BidListRepository
     */
    @Autowired
    BidListRepository bidListRepository;

    public BidListService(BidListRepository bidListRepository) {

    }

    /**
     * Find all bid list.
     *
     * @return bid list list
     */

    public List<BidList> findAll() {
        log.info("----------> find all bid ");
        return bidListRepository.findAll();
    }


    /**
     * Save bid list.
     *
     * @param bid the bid list to save
     */
    public void save(BidList bid) {
        log.info("---------------> save bid list" );
        bid.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        bid.setRevisionDate(Timestamp.valueOf(LocalDateTime.now()));
        bidListRepository.save(bid);

    }


    /**
     * Find bid list by id.
     *
     * @param id the bid list id
     * @return the bid list
     */

    public BidList findById(Integer id) {
        log.info("---------------> find bid by id" + id );
        return bidListRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
    }


    /**
     * Delete bid list by id.
     *
     * @param id the bid list id to delete
     */
    public void delete(Integer id) {
        log.info("--------------->  delete bid by id" + id );
        bidListRepository.deleteById(id);
    }
}
