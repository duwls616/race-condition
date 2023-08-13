package com.example.stock.facade;

import com.example.stock.repository.LockRepository;
import com.example.stock.repository.StockRepository;
import com.example.stock.service.StockService;
import org.springframework.stereotype.Component;

@Component
public class NamedLockStockFacade {
    private final LockRepository lockRepository;

    private final StockService stockService;

    public NamedLockStockFacade(LockRepository lockRepository, StockService stockService) {
        this.lockRepository = lockRepository;
        this.stockService = stockService;
    }

    public void decrease(Long id, Long quntity){
        try{
            lockRepository.getLock(id.toString());
            stockService.decrease(id, quntity);
        } finally {
            lockRepository.releaseLock(id.toString());
        }
    }
}
