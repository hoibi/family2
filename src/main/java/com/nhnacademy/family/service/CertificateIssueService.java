package com.nhnacademy.family.service;

import com.nhnacademy.family.entity.CertificateIssue;
import com.nhnacademy.family.entity.Resident;
import com.nhnacademy.family.repository.CertificateIssueRepository;
import com.nhnacademy.family.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class CertificateIssueService {
    private final CertificateIssueRepository certificateIssueRepository;

    private final ResidentService residentService;
    public CertificateIssue registerCertificateIssue(Integer residentNumber, String certificateType) {
        String  confirmationNumber = "";
        String certificateTypeCode;
        int randomRange = 0;
        for(int i = 0; i < 16; i++) {
            if (0 == i) {
                randomRange = 1;
            }
            int randomNum = ((int) (Math.random() * 9) + randomRange);
            confirmationNumber += randomNum;

        }

        if (certificateType.equals("family")) {
            certificateTypeCode = "가족관계 증명서";
        } else {
            certificateTypeCode = "주민등록본";
        }

        CertificateIssue certificateIssue = CertificateIssue.builder()
                .CertificateNumber(Long.parseLong(confirmationNumber))
                .resident(residentService.getResident(residentNumber))
                .certificateType(certificateTypeCode)
                .certificateIssueDate(LocalDate.now())
                .build();

        certificateIssueRepository.save(certificateIssue);

        return certificateIssue;
    }

    public CertificateIssue getCertificateIssue(Integer residentNumber, String certificateType) {
        String certificateTypeCode;
        if (certificateType.equals("family")) {
            certificateTypeCode = "가족관계증명서";
        } else {
            certificateTypeCode = "주민등록본";
        }
        return certificateIssueRepository.findByResident_IdAndCertificateType(residentNumber, certificateTypeCode);
    }

    public String getCertificateNumber(CertificateIssue certificateIssue) {
//        CertificateIssue certificateIssue = getCertificateIssue(residentNumber, certificateType);
        String confirmationNumber = String.valueOf(certificateIssue.getCertificateNumber());
        String preConfirmationNumber = confirmationNumber.substring(0, 7);
        String subConfirmationNumber = confirmationNumber.substring(8, 15);
        confirmationNumber = preConfirmationNumber + "-" + subConfirmationNumber;
        return confirmationNumber;
    }

    public LocalDate getCertificateIssueDate(Integer residentNumber, String certificateType) {
        CertificateIssue certificateIssue = getCertificateIssue(residentNumber, certificateType);
        return certificateIssue.getCertificateIssueDate();
    }

    @Transactional
    public void deleteCertificateIssue(Integer residentNumber) {
        certificateIssueRepository.deleteAllByResident_Id(residentNumber);
    }
}
