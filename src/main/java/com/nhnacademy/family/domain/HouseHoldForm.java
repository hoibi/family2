package com.nhnacademy.family.domain;

import com.nhnacademy.family.entity.Resident;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
public class HouseHoldForm {
    private Integer residentNumber;
    private LocalDate compositionDate;
    private String compositionReason;
    private String currentAddress;
}
