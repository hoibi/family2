package com.nhnacademy.family.domain;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class HouseholdMovementAddressForm {
    private LocalDate movementReportDate;
    private String movementAddress;
    private String lastAddress;
}
