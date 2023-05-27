package com.nhnacademy.family.controller;

import com.nhnacademy.family.entity.*;
import com.nhnacademy.family.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.JpaEntityGraph;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Controller
@RequestMapping("/chu")
@RequiredArgsConstructor
public class ChuController {
    private final BirthDeathReportService birthDeathReportService;
    private final FamilyRelationshipService familyRelationshipService;
    private final HouseholdMovementAddressService householdMovementAddressService;
    private final HouseHoldService houseHoldService;
    private final ResidentService residentService;
    private final CertificateIssueService certificateIssueService;
    private final HouseholdCompositionService householdCompositionService;

    @GetMapping("{serialNumber}/copy")
    public String getResidentRegistration(Model model, @PathVariable Integer serialNumber) {
        HouseholdCompositionResident householdCompositionResident = householdCompositionService.getHouseholdCompositionResident(serialNumber);
        if (householdCompositionResident == null) {
            return "redirect:/chu";
        }
        List<HouseholdCompositionResident> householdCompositionResidentList = householdCompositionService.getAllHouseholdCompositionResident(householdCompositionResident.getHouseHold().getHouseHoldNumber());
        HouseHold houseHold = houseHoldService.getHouseHold(householdCompositionResident.getHouseHold().getHouseHoldNumber());
        List<HouseHoldMovementAddress> houseHoldMovementAddressList = householdMovementAddressService.getHouseHoldMovementAddressListDesc(houseHold.getHouseHoldNumber());

        CertificateIssue certificateIssue = certificateIssueService.registerCertificateIssue(serialNumber, "copy");
        String certificationNumber = certificateIssueService.getCertificateNumber(certificateIssue);

        Resident householdResident = residentService.getResident(houseHold.getResident().getId());
        model.addAttribute("houseHold", houseHold);
        model.addAttribute("householdResident", householdResident);
        model.addAttribute("houseHoldMovementAddressList", houseHoldMovementAddressList);
        model.addAttribute("householdCompositionResidentList", householdCompositionResidentList);
        model.addAttribute("certificateIssue", certificateIssue);
        model.addAttribute("certificationNumber", certificationNumber);

        return "CopyOfResidentRegistration";
    }

    @GetMapping("{serialNumber}/family")
    public String getFamily(Model model, @PathVariable Integer serialNumber) {
        Resident resident = residentService.getResident(serialNumber);

        List<FamilyRelationship> familyRelationshipList = familyRelationshipService.getFamilyRelationship(serialNumber);

        CertificateIssue certificateIssue = certificateIssueService.registerCertificateIssue(serialNumber, "family");
        String certificationNumber = certificateIssueService.getCertificateNumber(certificateIssue);
//        CertificateIssue certificateIssue = certificateIssueService.getCertificateIssue(serialNumber, "family");
//        String certificateNumber = certificateIssueService.getCertificateNumber(serialNumber, "family");
//        LocalDate certificateIssueDate = certificateIssueService.getCertificateIssueDate(serialNumber, "family");

        model.addAttribute("resident", resident);
        model.addAttribute("familyRelationshipList", familyRelationshipList);
//        model.addAttribute("certificateNumber", certificateNumber);
//        model.addAttribute("certificateIssueDate", certificateIssueDate);
        model.addAttribute("certificateIssue", certificateIssue);
        model.addAttribute("certificationNumber", certificationNumber);
        return "FamilyRelationsCertificate";

    }

    @GetMapping
    public String getResident(Model model, @PageableDefault(page = 0, size = 4) Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        Page<Resident> residentList = residentService.getResidentByPage(pageable);
//        List<Resident> residentList1 = residentList.getContent();
//        for (Resident r : residentList1) {
//            if (r.hasBirthReport()) {
//                System.out.println("asdad");
//            }
//        }
        model.addAttribute("residentList", residentList);
        return "resident";
    }

    // ToDo serialNumber 로 resident 뽑아야 함
    // ToDo family_relationship으로 데이터 뽑아야 함

    @GetMapping("{serialNumber}/birth")
    public String getBirthForm(Model model, @PathVariable Integer serialNumber) {
        BirthDeathReport birthDeathReport = birthDeathReportService.getBirthDeathReportByResidentNumberAndTypeCode(serialNumber, "출생");

        if (Objects.isNull(birthDeathReport)) {
            throw new NoSuchElementException("출생신고서 없음");
        }

        Resident resident = residentService.getResident(serialNumber);

        Resident father = familyRelationshipService.getResidentByResidentIdAndRelationShip(serialNumber, "부");
        Resident mother = familyRelationshipService.getResidentByResidentIdAndRelationShip(serialNumber, "모");


        LocalDateTime birthDateTime = resident.getBirthDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분");
        String strBirthDateTime = birthDateTime.format(formatter);
        model.addAttribute("resident", resident);
        model.addAttribute("strBirthDateTime", strBirthDateTime);
        model.addAttribute("father", father);
        model.addAttribute("mother", mother);
        model.addAttribute("birthDeathReport", birthDeathReport);


        return "BirthRegistrationForm";
    }

    @GetMapping("{serialNumber}/death")
    public String getDeathForm(Model model, @PathVariable Integer serialNumber) {
        BirthDeathReport birthDeathReport = birthDeathReportService.getBirthDeathReportByResidentNumberAndTypeCode(serialNumber, "사망");

        if (Objects.isNull(birthDeathReport)) {
            throw new NoSuchElementException("출생신고서 없음");
        }

        Resident resident = residentService.getResident(serialNumber);


        LocalDateTime deathDate = resident.getDeathDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분");
        String strDeathDate = deathDate.format(formatter);

        model.addAttribute("resident", resident);
        model.addAttribute("strDeathDate", strDeathDate);
        model.addAttribute("birthDeathReport", birthDeathReport);
        return "DeathRegistrationForm";
    }


    @DeleteMapping("delete")
    public String deleteResident(@RequestParam Integer residentNumber) {

        if (houseHoldService.isHouseholdResident(residentNumber)) {
            if (0 != familyRelationshipService.hasFamily(residentNumber)) {
                return "redirect:/chu";
            } else {
                HouseHold houseHold = houseHoldService.getHouseholdByResidentId(residentNumber);
                householdMovementAddressService.deleteHouseHoldMovementAddressByHouseholdNumber(houseHold.getHouseHoldNumber());
            }
        }
        householdCompositionService.deleteAllHouseholdCompositionByResidentId(residentNumber);
        houseHoldService.deleteHouseHoldByResidentId(residentNumber);
        birthDeathReportService.deleteBirthReportByResidentNumber(residentNumber);
        familyRelationshipService.deleteFamilyRelationShipByResidentId(residentNumber);
        familyRelationshipService.deleteFamilyRelationShipByFamilyResidentId(residentNumber);
        certificateIssueService.deleteCertificateIssue(residentNumber);
        residentService.deleteResident(residentNumber);
        return "redirect:/chu";
    }
}
