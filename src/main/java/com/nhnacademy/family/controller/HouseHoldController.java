package com.nhnacademy.family.controller;

import com.nhnacademy.family.domain.HouseHoldForm;
import com.nhnacademy.family.entity.HouseHold;
import com.nhnacademy.family.service.HouseHoldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/household")
@RequiredArgsConstructor
public class HouseHoldController {
    private final HouseHoldService houseHoldService;
    @PostMapping
    public HttpEntity<Void> insertHouseHold(@RequestBody HouseHoldForm houseHoldForm) {
        houseHoldService.insertHouseHold(houseHoldForm);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("{householdSerialNumber}")
    public HttpEntity<Void> deleteHouseHold(@PathVariable Integer householdSerialNumber) {
        houseHoldService.deleteHouseHold(householdSerialNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
