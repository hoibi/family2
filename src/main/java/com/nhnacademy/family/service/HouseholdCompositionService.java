package com.nhnacademy.family.service;

import com.nhnacademy.family.entity.HouseholdCompositionResident;
import com.nhnacademy.family.entity.Resident;
import com.nhnacademy.family.repository.HouseholdCompositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseholdCompositionService {
    private final HouseholdCompositionRepository householdCompositionRepository;

    public HouseholdCompositionResident getHouseholdCompositionResident(Integer residentNumber) {
        return householdCompositionRepository.findByHouseholdCompositionResidentPk_ResidentSerialNumber(residentNumber);
    }

    public List<Resident> getResidentList(Integer householdNumber) {
        return householdCompositionRepository.findAllResidentByHouseholdCompositionResidentPk_HouseholdSerialNumber(householdNumber);
    }

    public List<HouseholdCompositionResident> getAllHouseholdCompositionResident(Integer householdNumber) {
        return householdCompositionRepository.findAllByHouseholdCompositionResidentPk_HouseholdSerialNumber(householdNumber);
    }

    @Transactional
    public void deleteAllHouseholdCompositionByResidentId(Integer residentNumber) {
        householdCompositionRepository.deleteAllByHouseholdCompositionResidentPk_ResidentSerialNumber(residentNumber);
    }
}
