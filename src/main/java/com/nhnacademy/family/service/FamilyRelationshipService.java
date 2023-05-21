package com.nhnacademy.family.service;

import com.nhnacademy.family.domain.FamilyRelationshipForm;
import com.nhnacademy.family.domain.FamilyResidentDto;
import com.nhnacademy.family.domain.RelationForm;
import com.nhnacademy.family.entity.FamilyRelationship;
import com.nhnacademy.family.entity.Resident;
import com.nhnacademy.family.repository.FamilyRelationshipRepository;
import com.nhnacademy.family.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class FamilyRelationshipService {
    private final FamilyRelationshipRepository repository;
    private final ResidentRepository residentRepository;
    public void saveFamilyRelationship(Integer serialNumber, FamilyRelationshipForm familyRelationshipForm) {
        FamilyRelationship.FamilyPk familyPk = new FamilyRelationship.FamilyPk(familyRelationshipForm.getFamilySerialNumber(), serialNumber);
        FamilyRelationship familyRelationship = FamilyRelationship.builder()
                .familyPk(familyPk)
                .familyResident(getResident(familyRelationshipForm.getFamilySerialNumber()))
                .resident(getResident(serialNumber))
                .relationShip(familyRelationshipForm.getRelationShip())
                .build();
        repository.save(familyRelationship);
    }

    public Resident getResident(Integer serialNumber) {
        return residentRepository.findById(serialNumber).orElseThrow(() -> new NoSuchElementException("해당 ID에 맞는 주민 없음"));
    }

    public List<FamilyRelationship> getAllFamilyRelationship() {
        return repository.findAll();
    }

    public void updateFamilyRelationship(Integer serialNumber, Integer familySerialNumber, RelationForm relationForm) {
        FamilyRelationship familyRelationship = getFamilyRelationship(serialNumber, familySerialNumber);
        familyRelationship.setRelationShip(relationForm.getRelationShip());
        repository.save(familyRelationship);
    }

    public FamilyRelationship getFamilyRelationship(Integer serialNumber, Integer familySerialNumber) {
        FamilyRelationship.FamilyPk familyPk = new FamilyRelationship.FamilyPk(familySerialNumber, serialNumber);
        return repository.findByFamilyPk(familyPk);
    }

    public void deleteFamilyRelationship(Integer serialNumber, Integer familySerialNumber) {
        FamilyRelationship.FamilyPk familyPk = new FamilyRelationship.FamilyPk(familySerialNumber, serialNumber);
        repository.deleteById(familyPk);
    }

    public List<Resident> getResidentByBaseResident(Integer baseResident) {
        List<FamilyRelationship> familyRelationshipList = repository.findByFamilyPk_ResidentSerialNumber(baseResident);

//        List<Integer> residentIds = new ArrayList<>();
//        for (FamilyRelationship f : familyRelationshipList) {
//            residentIds.add(f.getFamilyResident().getId());
//        }

        List<Resident> residentList = new ArrayList<>();
        for (FamilyRelationship f : familyRelationshipList) {
            residentList.add(f.getFamilyResident());
            f.getFamilyResident().getGender();
        }

        return residentList;

    }

    public List<FamilyRelationship> getFamilyRelationship(Integer baseResident) {
        return repository.findByFamilyPk_ResidentSerialNumber(baseResident);
    }

    public Resident getResidentByResidentIdAndRelationShip(Integer residentNumber, String relation) {
        FamilyResidentDto familyResidentDto = repository.findFamilyResidentByFamilyPk_ResidentSerialNumberAndRelationShip(residentNumber, relation);
        return familyResidentDto.getFamilyResident();
    }

    @Transactional
    public void deleteFamilyRelationShipByResidentId(Integer residentNumber) {
        repository.deleteAllByFamilyPk_ResidentSerialNumber(residentNumber);
    }

    @Transactional
    public void deleteFamilyRelationShipByFamilyResidentId(Integer residentNumber) {
        repository.deleteAllByFamilyPk_FamilySerialNumber(residentNumber);
    }

    public Integer hasFamily(Integer residentNumber) {
        return repository.countByFamilyPk_ResidentSerialNumber(residentNumber);
    }

}