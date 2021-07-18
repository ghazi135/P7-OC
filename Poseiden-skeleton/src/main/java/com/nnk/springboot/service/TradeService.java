package com.nnk.springboot.service;


import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
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
public class TradeService {

    /**
     * @see TradeRepository
     */
    @Autowired
    TradeRepository tradeRepository;


    /**
     * Find all trade.
     *
     * @return the trade list
     */

    public List<Trade> findAll() {
        log.info("---------------> find all Trades" );
        return tradeRepository.findAll();
    }


    /**
     * Save trade.
     *
     * @param trade the trade to save

     */
    public void save(Trade trade) {
        log.info("---------------> save trade" );
        trade.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        trade.setRevisionDate(Timestamp.valueOf(LocalDateTime.now()));
        tradeRepository.save(trade);
    }

    /**
     * Find trade by id.
     *
     * @param id the trade id
     * @return the trade
     */

    public Trade findById(Integer id) {
        log.info("---------------> find trade by id" + id );
        return tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
    }


    /**
     * Delete trade by id.
     *
     * @param id the trade id to delete
     */

    public void delete(Integer id) {
        log.info("---------------> delete trade by id" + id );
        tradeRepository.deleteById(id);
    }
}
