package com.nhnacademy.family.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table(name = "family_relationship")
public class FamilyRelationship {

    @EmbeddedId
    private FamilyPk familyPk;

    @MapsId("familySerialNumber")
    @ManyToOne
    @JoinColumn(name = "family_resident_serial_number")
    private Resident familyResident;

    @MapsId("residentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "base_resident_serial_number")
    private Resident resident;

    @Column(name = "family_relationship_code")
//    @Enumerated(EnumType.STRING)
    String relationShip;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    @Getter
    @Setter
    public static class FamilyPk implements Serializable {
        private Integer familySerialNumber;
        private Integer residentSerialNumber;

    }
//    public enum Relationship {
//        father, mother, spouse, child
//    }


}
