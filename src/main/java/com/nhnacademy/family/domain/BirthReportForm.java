package com.nhnacademy.family.domain;

import com.nhnacademy.family.entity.Resident;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.time.LocalDate;

@Getter
public class BirthReportForm {
    private Integer residentNumber;
    private String typeCode;
    private LocalDate reportDate;
    private String birthQualificationCode;
    private String email;
    private String phoneNumber;
}
