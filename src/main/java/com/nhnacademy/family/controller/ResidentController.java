package com.nhnacademy.family.controller;

import com.nhnacademy.family.domain.ResidentForm;
import com.nhnacademy.family.entity.Resident;
import com.nhnacademy.family.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residents")
@RequiredArgsConstructor
public class ResidentController {
    private final ResidentService service;
    @PostMapping
    public HttpEntity<Void> insertResident(@RequestBody ResidentForm residentForm) {
        service.saveResident(residentForm);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public HttpEntity<List<Resident>> getAllResident() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PutMapping("{serialNumber}")
    public HttpEntity<Void> updateResident(@PathVariable Integer serialNumber, @RequestBody ResidentForm residentForm) {
        service.updateResident(serialNumber, residentForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
