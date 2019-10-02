package com.moogu.driver.controller;

import com.moogu.driver.model.Driver;
import com.moogu.driver.repository.DriverRepository;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DriverController {
    private final DriverRepository driverRepository;

    public DriverController(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }


    @RequestMapping("/drivers")
    public List<Driver> getDrivers() {
        return driverRepository.findAll();
    }

    @GetMapping("/driver/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable(value = "id") Long driverId) throws NotFoundException {
        Driver driver = driverRepository.findById(driverId)
                                        .orElseThrow(() -> new NotFoundException("Driver not found on :: " + driverId));
        return ResponseEntity.ok().body(driver);
    }

    @PostMapping("/driver")
    public Driver createDriver(@Valid @RequestBody Driver driver) {
        return driverRepository.save(driver);
    }

    @PutMapping("/driver/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable(value = "id") Long driverId,
                                               @Valid @RequestBody Driver driverDetails) throws NotFoundException {
        Driver driver = driverRepository.findById(driverId)
                                        .orElseThrow(() -> new NotFoundException("Driver not found on :: " + driverId));
        driver.setName(driverDetails.getName());
        return ResponseEntity.ok(driverRepository.save(driver));
    }

    @DeleteMapping("/driver/{id}")
    public Map<String, Boolean> deleteDriver(@PathVariable(value = "id") Long driverId) throws Exception {
        Driver driver = driverRepository.findById(driverId)
                                        .orElseThrow(() -> new NotFoundException("Driver not found on :: " + driverId));
        driverRepository.delete(driver);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}