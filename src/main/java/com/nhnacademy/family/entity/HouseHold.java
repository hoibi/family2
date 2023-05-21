package com.nhnacademy.family.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "household")
public class HouseHold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "household_serial_number")
    private Integer houseHoldNumber;

    @OneToMany(mappedBy = "houseHold", cascade = CascadeType.REMOVE)
    List<HouseHoldMovementAddress> houseHoldMovementAddressList;

    @OneToMany(mappedBy = "houseHold", cascade = CascadeType.REMOVE)
    List<HouseholdCompositionResident> householdCompositionResidentList;


    @ManyToOne
    @JoinColumn(name = "household_resident_serial_number")
    private Resident resident;

    @Column(name = "household_composition_date")
    private LocalDate compositionDate;

    @Column(name = "household_composition_reason_code")
    private String compositionReason;

    @Column(name = "current_house_movement_address")
    private String currentAddress;

}
