package com.nhnacademy.family.entity;

import com.nhnacademy.family.domain.ResidentForm;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "resident")
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resident_serial_number")
    Integer id;

//    @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
//    List<FamilyRelationship> residentList;
//
//    @OneToMany(mappedBy = "familyResident", cascade = CascadeType.REMOVE)
//    List<FamilyRelationship> familyResidentList;

    @Setter
    @Column(nullable = false)
    String name;

    @Setter
    @Column(name = "resident_registration_number", nullable = false)
    String registrationNumber;

    @Setter
    @Column(name = "gender_code", nullable = false)
    String gender;
    @Setter
    @Column(name = "birth_date", nullable = false)
    LocalDateTime birthDate;
    @Setter
    @Column(name = "birth_place_code", nullable = false)
    String birthPlaceCode;
    @Setter
    @Column(name = "registration_base_address", nullable = false)
    String baseAddress;

    @Setter
    @Column(name = "death_date")
    LocalDateTime deathDate;
    @Setter
    @Column(name = "death_place_code")
    String deathPlaceCode;
    @Setter
    @Column(name = "death_place_address")
    String deathPlaceAddress;



    @Builder
    private Resident(String name, String registrationNumber, String gender, LocalDateTime birthDate, String birthPlaceCode, String baseAddress) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.gender = gender;
        this.birthDate = birthDate;
        this.birthPlaceCode = birthPlaceCode;
        this.baseAddress = baseAddress;
    }

    public static Resident toEntity(ResidentForm residentForm) {
        return Resident.builder()
                .name(residentForm.getName())
                .registrationNumber(residentForm.getRegistrationNumber())
                .gender(residentForm.getGender())
                .birthDate(residentForm.getBirthDate())
                .birthPlaceCode(residentForm.getBirthPlaceCode())
                .baseAddress(residentForm.getBaseAddress())
                .build();
    }
}