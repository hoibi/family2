package com.nhnacademy.family.service;

import com.nhnacademy.family.domain.ResidentForm;
import com.nhnacademy.family.entity.Resident;
import com.nhnacademy.family.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResidentService {
    private final ResidentRepository residentRepository;

    public void saveResident(ResidentForm residentForm) {
        residentRepository.save(Resident.toEntity(residentForm));
    }

    public List<Resident> findAll() {
        return residentRepository.findAll();
    }

    public Resident getResident(Integer serialNumber) {

        return residentRepository.findById(serialNumber).orElseThrow(() -> new NoSuchElementException("해당 ID에 맞는 놈 없음"));
    }
    @Transactional
    public void updateResident(Integer serialNumber, ResidentForm residentForm) {
        Resident resident = getResident(serialNumber);

        resident.setName(residentForm.getName());
        resident.setRegistrationNumber(residentForm.getRegistrationNumber());
        resident.setGender(residentForm.getGender());
        resident.setBirthDate(residentForm.getBirthDate());
        resident.setBirthPlaceCode(residentForm.getBirthPlaceCode());
        resident.setBaseAddress(residentForm.getBaseAddress());

        residentRepository.save(resident);
    }

    @Transactional
    public void deleteResident(Integer residentNumber) {
        residentRepository.deleteResidentById(residentNumber);
    }


    public Page<Resident> getResidentByPage(Pageable pageable) {
        Page<Resident> residents = residentRepository.findAll(pageable);
        Long counter = residents.getTotalElements();
        return new PageImpl<>(residents.getContent(), pageable, counter);
    }
}
