package com.api.stockproject.services;

import com.api.stockproject.models.StockMovement;
import com.api.stockproject.repositories.StockMovementRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private static final Logger logger = LogManager.getLogger(StockMovementService.class);

    @Autowired
    public StockMovementService(StockMovementRepository stockMovementRepository) {
        this.stockMovementRepository = stockMovementRepository;
    }

    // Create a stock movement
    public StockMovement createStockMovement(StockMovement stockMovement) {
        logger.info("StockMovement Completed");
        return stockMovementRepository.save(stockMovement);
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
    public StockMovement updateStockMovement(Long stockMovementId, StockMovement updatedStockMovement) {
        StockMovement existingStockMovement = getStockMovementById(stockMovementId);
        logger.info("Stock update completed.");
        return stockMovementRepository.save(existingStockMovement);
    }

    // Delete a stock movement
    public void deleteStockMovement(Long stockMovementId) {
        StockMovement stockMovement = getStockMovementById(stockMovementId);
        logger.info("Stock update completed.");
        stockMovementRepository.delete(stockMovement);
    }
}

