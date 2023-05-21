package com.nhnacademy.family.repository;

import com.nhnacademy.family.entity.HouseHold;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseHoldRepository extends JpaRepository<HouseHold, Integer> {

    boolean existsByResident_Id(Integer residentNumber);
//    Integer co

    Optional<HouseHold> findHouseHoldByResident_Id(Integer residentNumber);

    void deleteByResident_Id(Integer residentNumber);
}
