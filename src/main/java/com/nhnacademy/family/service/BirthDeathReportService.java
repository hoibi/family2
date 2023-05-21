package com.nhnacademy.family.service;

import com.nhnacademy.family.domain.*;
import com.nhnacademy.family.entity.BirthDeathReport;
import com.nhnacademy.family.entity.Resident;
import com.nhnacademy.family.repository.BirthDeathReportRepository;
import com.nhnacademy.family.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BirthDeathReportService {
    private final BirthDeathReportRepository birthDeathReportRepository;
    private final ResidentRepository residentRepository;

    @Transactional
    public void registerBirthReport(Integer reportNumber, BirthReportForm birthReportForm) {

        BirthDeathReport.BirthDeathPk birthDeathPk = new BirthDeathReport.BirthDeathPk(birthReportForm.getResidentNumber(), birthReportForm.getTypeCode(), reportNumber);
        BirthDeathReport birthDeathReport = BirthDeathReport.builder()
                .birthDeathPk(birthDeathPk)
                .resident(getResident(birthReportForm.getResidentNumber()))
                .reportResident(getResident(reportNumber))
                .reportDate(birthReportForm.getReportDate())
                .birthQualificationCode(birthReportForm.getBirthQualificationCode())
                .email(birthReportForm.getEmail())
                .phoneNumber(birthReportForm.getPhoneNumber())
                .build();
        birthDeathReportRepository.save(birthDeathReport);
    }

    public void updateBirthReport(Integer serialNumber, Integer targetSerialNumber, BirthChangeForm birthChangeForm) {
        BirthDeathReport birthDeathReport = getBirthDeathReport(serialNumber, targetSerialNumber, birthChangeForm.getTypeCode());

        birthDeathReport.setReportDate(birthChangeForm.getReportDate());
        birthDeathReport.setBirthQualificationCode(birthChangeForm.getBirthQualificationsCode());
        birthDeathReport.setEmail(birthChangeForm.getEmail());
        birthDeathReport.setPhoneNumber(birthChangeForm.getPhoneNumber());

        birthDeathReportRepository.save(birthDeathReport);
    }

    public void deleteBirthReport(Integer serialNumber, Integer targetSerialNumber, BirthDeleteForm birthDeleteForm) {
        BirthDeathReport birthDeathReport = getBirthDeathReport(serialNumber, targetSerialNumber, birthDeleteForm.getTypeCode());
        birthDeathReportRepository.delete(birthDeathReport);
    }

    public void registerDeathReport(Integer reportNumber, DeathReportForm deathReportForm) {
        BirthDeathReport.BirthDeathPk birthDeathPk = new BirthDeathReport.BirthDeathPk(deathReportForm.getResidentNumber(), deathReportForm.getTypeCode(), reportNumber);
        BirthDeathReport birthDeathReport = BirthDeathReport.builder()
                .birthDeathPk(birthDeathPk)
                .resident(getResident(deathReportForm.getResidentNumber()))
                .reportResident(getResident(reportNumber))
                .reportDate(deathReportForm.getReportDate())
                .deathQualificationCode(deathReportForm.getDeathQualificationCode())
                .email(deathReportForm.getEmail())
                .phoneNumber(deathReportForm.getPhoneNumber())
                .build();
        birthDeathReportRepository.save(birthDeathReport);

        // TODO 여기욤
        Resident resident = getResident(deathReportForm.getResidentNumber());
        resident.setDeathDate(deathReportForm.getDeathDate());
        resident.setDeathPlaceCode(deathReportForm.getDeathPlaceCode());
        resident.setDeathPlaceAddress(deathReportForm.getDeathPlaceAddress());

        residentRepository.save(resident);
    }

    public void updateDeathReport(Integer serialNumber, Integer targetSerialNumber, DeathChangeForm deathChangeForm) {
        BirthDeathReport birthDeathReport = getBirthDeathReport(serialNumber, targetSerialNumber, deathChangeForm.getTypeCode());

        birthDeathReport.setReportDate(deathChangeForm.getReportDate());
        birthDeathReport.setBirthQualificationCode(deathChangeForm.getDeathQualificationsCode());
        birthDeathReport.setEmail(deathChangeForm.getEmail());
        birthDeathReport.setPhoneNumber(deathChangeForm.getPhoneNumber());

        birthDeathReportRepository.save(birthDeathReport);
    }

    public void deleteDeathReport(Integer serialNumber, Integer targetSerialNumber, DeathDeleteForm deathDeleteForm) {
        BirthDeathReport birthDeathReport = getBirthDeathReport(serialNumber, targetSerialNumber, deathDeleteForm.getTypeCode());
        birthDeathReportRepository.delete(birthDeathReport);
    }
    public Resident getResident(Integer serialNumber) {
        return residentRepository.findById(serialNumber).orElseThrow(() -> new NoSuchElementException("해당 아이디에 적합한 주민 없음"));
    }

    public List<BirthDeathReport> getAllBirthDeathReport() {
        return birthDeathReportRepository.findAll();
    }

    public BirthDeathReport getBirthDeathReport(Integer serialNumber, Integer targetSerialNumber, String typeCOde) {
        BirthDeathReport.BirthDeathPk birthDeathPk = getPk(serialNumber, targetSerialNumber, typeCOde);
        return birthDeathReportRepository.findByBirthDeathPk(birthDeathPk);

    }

    public BirthDeathReport.BirthDeathPk getPk(Integer serialNumber, Integer targetSerialNumber, String typeCode) {
        return new BirthDeathReport.BirthDeathPk(targetSerialNumber, typeCode, serialNumber);
    }

    public BirthDeathReport getBirthDeathReportByResidentNumberAndTypeCode(Integer residentNumber, String typeCode) {
        return birthDeathReportRepository.findBirthDeathReportByBirthDeathPk_ResidentNumberAndBirthDeathPk_TypeCode(residentNumber, typeCode);
    }

    @Transactional
    public void deleteBirthReportByResidentNumber(Integer residentNumber) {
        birthDeathReportRepository.deleteAllByBirthDeathPk_ResidentNumber(residentNumber);
    }

}
