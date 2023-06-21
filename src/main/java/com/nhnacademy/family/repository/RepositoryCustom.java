package com.nhnacademy.family.repository;

import com.nhnacademy.family.entity.BirthDeathReport;
import com.nhnacademy.family.entity.CertificateIssue;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RepositoryCustom {

    BirthDeathReport findBirthDeathReportByBirthDeathPk_ResidentNumberAndBirthDeathPk_TypeCode(Integer residentNumber, String typeCode);
}
