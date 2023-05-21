package com.nhnacademy.family.repository;

import com.nhnacademy.family.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {

//    Resident findById(Integer residentId);
    void deleteResidentById(Integer residentNumber);

    Page<Resident> findAll(Pageable pageable);
}
