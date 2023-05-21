package com.nhnacademy.family.service;

import com.nhnacademy.family.domain.HouseHoldForm;
import com.nhnacademy.family.entity.HouseHold;
import com.nhnacademy.family.entity.Resident;
import com.nhnacademy.family.repository.HouseHoldRepository;
import com.nhnacademy.family.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HouseHoldService {
    private final HouseHoldRepository houseHoldRepository;
    private final ResidentRepository residentRepository;

    public Resident getResident(Integer residentNumber) {
        return residentRepository.findById(residentNumber).orElseThrow(() -> new NoSuchElementException("해당 아이디에 적합한 resident 없음"));
    }

    public HouseHold getHouseHold(Integer householdSerialNumber) {
        return houseHoldRepository.findById(householdSerialNumber).orElseThrow(() -> new NoSuchElementException("해당 아이디에 대한 household 없음"));
    }
    public void insertHouseHold(HouseHoldForm houseHoldForm) {
        HouseHold houseHold = HouseHold.builder()
                .resident(getResident(houseHoldForm.getResidentNumber()))
                .compositionDate(houseHoldForm.getCompositionDate())
                .compositionReason(houseHoldForm.getCompositionReason())
                .currentAddress(houseHoldForm.getCurrentAddress())
                .build();
        houseHoldRepository.save(houseHold);
    }

    public void deleteHouseHold(Integer householdSerialNumber) {
//        HouseHold houseHold = getHouseHold(householdSerialNumber);
//        houseHoldRepository.delete(houseHold);
        houseHoldRepository.deleteById(householdSerialNumber);
    }

    public List<HouseHold> getAllHouseHold() {
        return houseHoldRepository.findAll();
    }

    public boolean isHouseholdResident(Integer residentNumber) {
        return houseHoldRepository.existsByResident_Id(residentNumber);
    }

    public HouseHold getHouseholdByResidentId(Integer residentNumber) {
        Optional<HouseHold> houseHold = houseHoldRepository.findHouseHoldByResident_Id(residentNumber);
        if (Objects.isNull(houseHold)) {
            throw new NoSuchElementException("Household no exits");
        }
        return houseHold.get();
    }

    @Transactional
    public void deleteHouseHoldByResidentId(Integer residentNumber) {
        houseHoldRepository.deleteByResident_Id(residentNumber);
    }
}
