package com.nhnacademy.family.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

// TODO cascade 넣으셈
@Entity
@Table(name = "household_composition_resident")
@Getter
public class HouseholdCompositionResident {
    @EmbeddedId
    private HouseholdCompositionResidentPk householdCompositionResidentPk;

    @MapsId("householdSerialNumber")
    @ManyToOne
    @JoinColumn(name = "household_serial_number")
    private HouseHold houseHold;

    @MapsId("residentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @Column(name = "report_date")
    private LocalDate reportDate;

    @Column(name = "household_relationship_code")
    private String relationshipCode;

    @Column(name = "household_composition_change_reason_code")
    private String changeReasonCode;

    @Embeddable
    @EqualsAndHashCode
    public static class HouseholdCompositionResidentPk implements Serializable {
        private Integer householdSerialNumber;
        private Integer residentSerialNumber;
    }
}
