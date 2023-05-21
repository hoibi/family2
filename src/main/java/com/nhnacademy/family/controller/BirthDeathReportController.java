package com.nhnacademy.family.controller;

import com.nhnacademy.family.domain.*;
import com.nhnacademy.family.entity.BirthDeathReport;
import com.nhnacademy.family.service.BirthDeathReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/residents/{serialNumber}")
@RequiredArgsConstructor
public class BirthDeathReportController {
    private final BirthDeathReportService birthDeathReportService;

    @PostMapping("birth")
    public HttpEntity<Void> registerBirthReport(@PathVariable Integer serialNumber, @RequestBody BirthReportForm birthReportForm) {
        birthDeathReportService.registerBirthReport(serialNumber, birthReportForm);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public HttpEntity<List<BirthDeathReport>> getAllBirthDeathReport(@PathVariable Integer serialNumber) {
        return new ResponseEntity<>(birthDeathReportService.getAllBirthDeathReport(), HttpStatus.OK);
    }

    @PutMapping("birth/{targetSerialNumber}")
    public HttpEntity<Void> updateBirthReport(@PathVariable(name = "serialNumber") Integer serialNumber,
                                              @PathVariable(name = "targetSerialNumber") Integer targetSerialNumber,
                                              @RequestBody BirthChangeForm birthChangeForm) {
        birthDeathReportService.updateBirthReport(serialNumber, targetSerialNumber, birthChangeForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("birth/{targetSerialNumber}")
    public HttpEntity<Void> deleteBirthReport(@PathVariable(name = "serialNumber") Integer serialNumber,
                                              @PathVariable(name = "targetSerialNumber") Integer targetSerialNumber,
                                                @RequestBody BirthDeleteForm birthDeleteForm) {
        birthDeathReportService.deleteBirthReport(serialNumber, targetSerialNumber, birthDeleteForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("death")
    public HttpEntity<Void> registerDeathReport(@PathVariable Integer serialNumber, @RequestBody DeathReportForm deathReportForm) {
        birthDeathReportService.registerDeathReport(serialNumber, deathReportForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // TODO DeathChangeForm에 수정할 수 있는 데이터가 하나밖에 없다. 결국 다른 것도 다 set을 해줘야 하는데 null이 들어가면 맛이 갈것이다
    // 그것을 고쳐야 한다.
    @PutMapping("death/{targetSerialNumber}")
    public HttpEntity<Void> updateDeathReport(@PathVariable(name = "serialNumber") Integer serialNumber,
                                              @PathVariable(name = "targetSerialNumber") Integer targetSerialNumber,
                                              @RequestBody DeathChangeForm deathChangeForm) {
        birthDeathReportService.updateDeathReport(serialNumber, targetSerialNumber, deathChangeForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("death/{targetSerialNumber}")
    public HttpEntity<Void> deleteDeathReport(@PathVariable(name = "serialNumber") Integer serialNumber,
                                              @PathVariable(name = "targetSerialNumber") Integer targetSerialNumber,
                                              @RequestBody DeathDeleteForm deathDeleteForm) {
        birthDeathReportService.deleteDeathReport(serialNumber, targetSerialNumber, deathDeleteForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
