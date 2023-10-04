package com.api.stockproject.services;

import com.api.stockproject.controllers.ItemController;
import com.api.stockproject.dtos.StockMovementDto;
import com.api.stockproject.models.Item;
import com.api.stockproject.models.StockMovement;
import com.api.stockproject.repositories.StockMovementRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    @Autowired
    ItemController itemController;
    private static final Logger logger = LogManager.getLogger(StockMovementService.class);

    @Autowired
    public StockMovementService(StockMovementRepository stockMovementRepository) {
        this.stockMovementRepository = stockMovementRepository;
    }

    // Create a stock movement
    public StockMovement createStockMovement(StockMovementDto stockMovementDto) {
        logger.info("StockMovement Completed");
        return stockMovementRepository.save(converterDto(stockMovementDto));
    }

    // Read all stock movements
    public List<StockMovement> getAllStockMovements() {
        return stockMovementRepository.findAll();
    }

    // Read a stock movement by ID
    public StockMovement getStockMovementById(Long stockMovementId) {
        return stockMovementRepository.findById(stockMovementId)
                .orElseThrow(() -> new EntityNotFoundException("StockMovement not found with id: " + stockMovementId));
    }

    // Update a stock movement
    public StockMovement updateStockMovement(Long stockMovementId, StockMovementDto updatedStockMovementDto) {
        StockMovement existingStockMovement = getStockMovementById(stockMovementId);
        if(updatedStockMovementDto.getQuantity() > 0){
            existingStockMovement.setQuantity(updatedStockMovementDto.getQuantity());
        }
        logger.info("Stock update completed.");
        return stockMovementRepository.save(existingStockMovement);
    }

    // Delete a stock movement
    public void deleteStockMovement(Long stockMovementId) {
        StockMovement stockMovement = getStockMovementById(stockMovementId);
        logger.info("Stock update completed.");
        stockMovementRepository.delete(stockMovement);
    }

    public StockMovement converterDto(StockMovementDto stockMovementDto){
        StockMovement stockMovement = new StockMovement();
        if(stockMovementDto != null &&  stockMovementDto.getItem_id() != null){
            Item item = itemController.getItemById(stockMovementDto.getItem_id());
            if(item.getId() != null){
                stockMovement.setItem(item);
            }
        }
        stockMovement.setCreationDate(Date.valueOf(LocalDate.now()));
        stockMovement.setQuantity(stockMovementDto.getQuantity());
        return stockMovement;
    }
}

