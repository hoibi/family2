package com.nhnacademy.family.repository;

import com.nhnacademy.family.domain.FamilyResidentDto;
import com.nhnacademy.family.entity.FamilyRelationship;
import com.nhnacademy.family.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.FamilyPk> {
     FamilyRelationship findByFamilyPk(FamilyRelationship.FamilyPk familyPk);

     @Query("SELECT fr FROM FamilyRelationship fr WHERE fr.familyPk.residentSerialNumber = :baseResident")
     List<FamilyRelationship> findByFamilyPk_ResidentSerialNumber(@Param("baseResident") Integer baseResident);

     FamilyResidentDto findFamilyResidentByFamilyPk_ResidentSerialNumberAndRelationShip(Integer residentSerialNumber, String relation);

     void deleteAllByFamilyPk_ResidentSerialNumber(Integer residentNumber);
     void deleteAllByFamilyPk_FamilySerialNumber(Integer residentNumber);

     Integer countByFamilyPk_ResidentSerialNumber(Integer residentNumber);
}
