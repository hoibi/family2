package com.nhnacademy.family.repository;

import com.nhnacademy.family.entity.HouseHoldMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HouseholdMovementAddressRepository extends JpaRepository<HouseHoldMovementAddress, HouseHoldMovementAddress.MovementAddressPk> {
    List<HouseHoldMovementAddress> findByHouseHold_HouseHoldNumber(Integer householdSerialNumber);
    List<HouseHoldMovementAddress> findAllByHouseHold_HouseHoldNumberOrderByMovementAddressPk_MovementReportDateDesc(Integer householdSerialNumber);

    HouseHoldMovementAddress findByMovementAddressPk(HouseHoldMovementAddress.MovementAddressPk movementAddressPk);

//    HouseHoldMovementAddress findByLastAddressAndMovementAddressPk(String lastAddress, HouseHoldMovementAddress.MovementAddressPk);
//    HouseHoldMovementAddress findByhouseholdNuPkAndLastAddress(Integer houseHoldNumber, String lastAddress);
    HouseHoldMovementAddress findByHouseHold_HouseHoldNumberAndLastAddress(Integer houseHoldNumber, String lastAddress);

    void deleteAllByMovementAddressPk_HouseholdSerialNumber(Integer householdNumber);
}
