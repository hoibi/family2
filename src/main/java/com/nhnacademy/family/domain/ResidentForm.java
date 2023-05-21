package com.nhnacademy.family.domain;

import com.nhnacademy.family.entity.Resident;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ResidentForm {
    String name;
    String registrationNumber;
    String gender;
    LocalDateTime birthDate;
    String birthPlaceCode;
    String baseAddress;
}
