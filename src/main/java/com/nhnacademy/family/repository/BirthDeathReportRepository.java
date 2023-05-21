package com.nhnacademy.family.repository;

import com.nhnacademy.family.entity.BirthDeathReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthDeathReportRepository extends JpaRepository<BirthDeathReport, BirthDeathReport.BirthDeathPk> {
    BirthDeathReport findByBirthDeathPk(BirthDeathReport.BirthDeathPk birthDeathPk);

    BirthDeathReport findBirthDeathReportByBirthDeathPk_ResidentNumberAndBirthDeathPk_TypeCode(Integer residentNumber, String typeCode);

    void deleteAllByBirthDeathPk_ResidentNumber(Integer residentNumber);

}
