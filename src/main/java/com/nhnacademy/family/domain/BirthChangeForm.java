package com.nhnacademy.family.domain;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BirthChangeForm {
    private String typeCode;
    private LocalDate reportDate;
    // TODO 추가
    private String birthQualificationsCode;
    private String email;
    private String phoneNumber;


}
