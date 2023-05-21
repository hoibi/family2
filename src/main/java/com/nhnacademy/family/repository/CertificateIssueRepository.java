package com.nhnacademy.family.repository;

import com.nhnacademy.family.entity.CertificateIssue;
import com.nhnacademy.family.service.CertificateIssueService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long> {

    CertificateIssue findByResident_IdAndCertificateType(Integer residentNumber, String certificateType);

    void deleteAllByResident_Id(Integer resident);
}
