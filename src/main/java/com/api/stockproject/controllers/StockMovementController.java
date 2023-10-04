package com.api.stockproject.controllers;

import com.api.stockproject.models.StockMovement;
import com.api.stockproject.services.StockMovementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/stockmovements")
public class StockMovementController {

    private final StockMovementService stockMovementService;
    private static final Logger logger = LogManager.getLogger(StockMovementController.class);

    @Autowired
    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    // Create a stock movement
    @PostMapping("/")
    public StockMovement createStockMovement(@RequestBody StockMovement stockMovement) {
        logger.info("starting the creation of StockMovements...");
        stockMovement.setCreationDate(Date.valueOf(LocalDate.now()));
        return stockMovementService.createStockMovement(stockMovement);
    }

    // Read all stock movements
    @GetMapping("/")
    public List<StockMovement> getAllStockMovements() {
        return stockMovementService.getAllStockMovements();
    }

    // Read a stock movement by ID
    @GetMapping("/{stockMovementId}")
    public StockMovement getStockMovementById(@PathVariable Long stockMovementId) {
        return stockMovementService.getStockMovementById(stockMovementId);
    }

    // Update a stock movement
    @PutMapping("/{stockMovementId}")
    public StockMovement updateStockMovement(@PathVariable Long stockMovementId, @RequestBody StockMovement stockMovement) {
        logger.info("starting the update of StockMovements...");
        return stockMovementService.updateStockMovement(stockMovementId, stockMovement);
    }

    // Delete a stock movement
    @DeleteMapping("/{stockMovementId}")
    public void deleteStockMovement(@PathVariable Long stockMovementId) {
        logger.info("starting the update of StockMovements...");
        stockMovementService.deleteStockMovement(stockMovementId);
    }
}
