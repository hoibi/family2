package com.nhnacademy.family.controller;


import com.nhnacademy.family.domain.HouseholdMovementAddressForm;
import com.nhnacademy.family.domain.HouseholdMovementAddressUpdateForm;
import com.nhnacademy.family.service.HouseholdMovementAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
@RestController
@RequestMapping("/household/{householdSerialNumber}/movement")
@RequiredArgsConstructor
public class HouseholdMovementAddressController {
    private final HouseholdMovementAddressService householdMovementAddressService;

    @PostMapping
    public HttpEntity<Void> insertHouseholdMovementAddress(@PathVariable Integer householdSerialNumber,
                                                           @RequestBody HouseholdMovementAddressForm householdMovementAddressForm) {
        householdMovementAddressService.insertHouseholdMovementAddress(householdSerialNumber, householdMovementAddressForm);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{reportDate}")
    public HttpEntity<Void> updateHouseholdMovementAddress(@PathVariable(name = "householdSerialNumber") Integer householdSerialNumber,
                                                           @PathVariable(name = "reportDate") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate reportDate, @RequestBody HouseholdMovementAddressUpdateForm householdMovementAddressUpdateForm) {
        householdMovementAddressService.updateHouseholdMovementAddress(householdSerialNumber, reportDate, householdMovementAddressUpdateForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{reportDate}")
    public HttpEntity<Void> deleteHouseholdMovementAddress(@PathVariable(name = "householdSerialNumber") Integer householdSerialNumber,
                                                           @PathVariable(name = "reportDate") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate reportDate) {
        householdMovementAddressService.deleteHouseholdMovementAddress(householdSerialNumber, reportDate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
