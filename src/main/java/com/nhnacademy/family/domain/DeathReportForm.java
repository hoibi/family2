package com.nhnacademy.family.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class DeathReportForm {
    private Integer residentNumber;
    private String typeCode;
    private LocalDate reportDate;
    private String deathQualificationCode;
    private String email;
    private String phoneNumber;
    private LocalDateTime deathDate;
    private String deathPlaceCode;
    private String deathPlaceAddress;
}
