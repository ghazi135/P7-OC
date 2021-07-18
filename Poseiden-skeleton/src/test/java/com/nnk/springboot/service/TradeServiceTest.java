package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TradeServiceTest {

    private static List<Trade> tradeList;
    @InjectMocks
    private TradeService tradeService;
    @Mock
    private TradeRepository tradeRepository;

    @BeforeEach
    void setUpPerTest() {

        tradeList = new ArrayList<>();
        tradeList.add(new Trade("test", "test"));
        tradeList.add(new Trade("test", "test"));
    }

    @Test
    public void should_Return_All_Trades() {

        when(tradeRepository.findAll()).thenReturn(tradeList);
        tradeService.findAll();
        verify(tradeRepository).findAll();
    }

    @Test
    public void should_Save_Trade() {

        Trade trade = new Trade("test", "test");
        when(tradeRepository.save(trade)).thenReturn(trade);
        tradeService.save(trade);
        verify(tradeRepository).save(trade);
    }

    @Test
    public void should_Return_Trade_By_Id() {

        Trade trade = new Trade("test", "test");
        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));
        tradeService.findById(1);
        verify(tradeRepository).findById(1);
    }

    @Test
    public void should_Delete_Trade_By_Id() {

        doNothing().when(tradeRepository).deleteById(1);
        tradeService.delete(1);
        verify(tradeRepository).deleteById(1);
    }
}
