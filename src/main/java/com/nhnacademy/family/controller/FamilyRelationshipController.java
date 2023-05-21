package com.nhnacademy.family.controller;

import com.nhnacademy.family.domain.FamilyRelationshipForm;
import com.nhnacademy.family.domain.RelationForm;
import com.nhnacademy.family.entity.FamilyRelationship;
import com.nhnacademy.family.service.FamilyRelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residents/{serialNumber}/relationship")
@RequiredArgsConstructor
public class FamilyRelationshipController {
    private final FamilyRelationshipService service;

    @PostMapping
    public HttpEntity<Void> insertFamilyRelationship(@PathVariable Integer serialNumber, @RequestBody FamilyRelationshipForm familyRelationshipForm) {
        service.saveFamilyRelationship(serialNumber, familyRelationshipForm);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
   public HttpEntity<List<FamilyRelationship>> getAllFamilyRelationship() {
        return new ResponseEntity<>(service.getAllFamilyRelationship(), HttpStatus.OK);
    }

    @PutMapping("{familySerialNumber}")
    public HttpEntity<Void> updateFamilyRelationship(@PathVariable(name = "serialNumber") Integer serialNumber
            , @PathVariable(name = "familySerialNumber") Integer familySerialNumber
            , @RequestBody RelationForm relationForm) {
        service.updateFamilyRelationship(serialNumber, familySerialNumber, relationForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{familySerialNumber}")
    public HttpEntity<Void> deleteFamilyRelationship(@PathVariable(name = "serialNumber") Integer serialNumber
            , @PathVariable(name = "familySerialNumber") Integer familySerialNumber) {
        service.deleteFamilyRelationship(serialNumber, familySerialNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
