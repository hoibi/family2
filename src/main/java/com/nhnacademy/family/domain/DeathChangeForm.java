package com.nhnacademy.family.domain;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DeathChangeForm {
    private String typeCode;
    private LocalDate reportDate;

    // TODO 추가
    private String deathQualificationsCode;
    private String email;
    private String phoneNumber;
}
