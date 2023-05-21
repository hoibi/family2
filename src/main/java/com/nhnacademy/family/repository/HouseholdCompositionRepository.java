package com.nhnacademy.family.repository;

import com.nhnacademy.family.entity.HouseholdCompositionResident;
import com.nhnacademy.family.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseholdCompositionRepository extends JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.HouseholdCompositionResidentPk> {
    HouseholdCompositionResident findByHouseholdCompositionResidentPk_ResidentSerialNumber(Integer residentNumber);

    List<HouseholdCompositionResident> findAllByHouseholdCompositionResidentPk_HouseholdSerialNumber(Integer householdNumber);
    List<Resident> findAllResidentByHouseholdCompositionResidentPk_HouseholdSerialNumber(Integer householdSerialNumber);

    void deleteAllByHouseholdCompositionResidentPk_ResidentSerialNumber(Integer residentNumber);
}
