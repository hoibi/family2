package com.nhnacademy.family.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

// ToDo 컨트롤러 서비스 레파지토리 작성
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "household_movement_address")
public class HouseHoldMovementAddress {
    @EmbeddedId
    private MovementAddressPk movementAddressPk;

    @MapsId("householdSerialNumber")
    @ManyToOne
    @JoinColumn(name = "household_serial_number")
    private HouseHold houseHold;

    @Column(name = "house_movement_address")
    private String movementAddress;

    @Column(name = "last_address_yn")
    private String lastAddress;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    @Getter
    public static class MovementAddressPk implements Serializable {

        @Column(name = "house_movement_report_date")
        private LocalDate movementReportDate;
        private Integer householdSerialNumber;

    }
}
