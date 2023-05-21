package com.nhnacademy.family.domain;

import com.nhnacademy.family.entity.FamilyRelationship;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FamilyRelationshipForm {
    Integer familySerialNumber;
    String relationShip;
}
