package com.nhnacademy.family.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table(name = "birth_death_report_resident")
public class BirthDeathReport {
    @EmbeddedId
    private BirthDeathPk birthDeathPk;

    @MapsId("residentNumber")
    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @MapsId("reportSerialNumber")
    @ManyToOne
    @JoinColumn(name = "report_resident_serial_number")
    private Resident reportResident;

    @Column(name = "birth_death_report_date")
    private LocalDate reportDate;

    @Column(name = "birth_report_qualifications_code")
    private String birthQualificationCode;

    @Column(name = "death_report_qualifications_code")
    private String deathQualificationCode;

    @Column(name = "email_address")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @NoArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    @AllArgsConstructor
    @Embeddable
    public static class BirthDeathPk implements Serializable {
        private Integer residentNumber;
        @Column(name = "birth_death_type_code")
        private String typeCode;
        private Integer reportSerialNumber;

    }
}
