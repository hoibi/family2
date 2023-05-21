package com.nhnacademy.family.service;

import com.nhnacademy.family.domain.HouseholdMovementAddressForm;
import com.nhnacademy.family.domain.HouseholdMovementAddressUpdateForm;
import com.nhnacademy.family.entity.HouseHold;
import com.nhnacademy.family.entity.HouseHoldMovementAddress;
import com.nhnacademy.family.repository.HouseholdMovementAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HouseholdMovementAddressService {
    private final HouseholdMovementAddressRepository householdMovementAddressRepository;
    private final HouseHoldService houseHoldService;

    public HouseHold getHouseHold(Integer householdSerialNumber) {
        return houseHoldService.getHouseHold(householdSerialNumber);
    }

    public List<HouseHoldMovementAddress> getHouseHoldMovementAddressList(Integer householdSerialNumber) {
        return householdMovementAddressRepository.findByHouseHold_HouseHoldNumber(householdSerialNumber);
    }
    public List<HouseHoldMovementAddress> getHouseHoldMovementAddressListDesc(Integer householdSerialNumber) {
        return householdMovementAddressRepository.findAllByHouseHold_HouseHoldNumberOrderByMovementAddressPk_MovementReportDateDesc(householdSerialNumber);
    }

    // ToDo 값을 저장할 때 이미 이전 주소가 있다면 그 데이터의 last_address_yn에 n를 설정해줘야 함
    public void insertHouseholdMovementAddress(Integer householdSerialNumber, HouseholdMovementAddressForm householdMovementAddressForm) {
//        List<HouseHoldMovementAddress> houseHoldMovementAddressList = getHouseHoldMovementAddressList(householdSerialNumber);
//        houseHoldMovementAddressList.forEach(h -> h.setLastAddress("N"));
//        householdMovementAddressRepository.saveAll(houseHoldMovementAddressList);
        HouseHoldMovementAddress houseHoldMovementAddress2 = householdMovementAddressRepository.findByHouseHold_HouseHoldNumberAndLastAddress(householdSerialNumber, "Y");
        if(Objects.nonNull(houseHoldMovementAddress2)) {
            houseHoldMovementAddress2.setLastAddress("N");
            householdMovementAddressRepository.save(houseHoldMovementAddress2);
        }
        HouseHoldMovementAddress.MovementAddressPk movementAddressPk = new HouseHoldMovementAddress.MovementAddressPk(householdMovementAddressForm.getMovementReportDate(), householdSerialNumber);
        HouseHoldMovementAddress houseHoldMovementAddress = HouseHoldMovementAddress.builder()
                .movementAddressPk(movementAddressPk)
                .houseHold(getHouseHold(householdSerialNumber))
                .movementAddress(householdMovementAddressForm.getMovementAddress())
                .lastAddress(householdMovementAddressForm.getLastAddress())
                .build();
        householdMovementAddressRepository.save(houseHoldMovementAddress);
    }

    public HouseHoldMovementAddress.MovementAddressPk getPk(Integer householdSerialNumber, LocalDate reportDate) {
        return new HouseHoldMovementAddress.MovementAddressPk(reportDate, householdSerialNumber);
    }

    public void updateHouseholdMovementAddress(Integer householdSerialNumber, LocalDate reportDate, HouseholdMovementAddressUpdateForm householdMovementAddressUpdateForm) {
        HouseHoldMovementAddress houseHoldMovementAddress = householdMovementAddressRepository.findByMovementAddressPk(getPk(householdSerialNumber, reportDate));
        houseHoldMovementAddress.setMovementAddress(householdMovementAddressUpdateForm.getMovementAddress());
        householdMovementAddressRepository.save(houseHoldMovementAddress);
    }

    public void deleteHouseholdMovementAddress(Integer householdSerialNumber, LocalDate reportDate) {
        householdMovementAddressRepository.deleteById(getPk(householdSerialNumber, reportDate));
    }

    @Transactional
    public void deleteHouseHoldMovementAddressByHouseholdNumber(Integer householdNumber) {
        householdMovementAddressRepository.deleteAllByMovementAddressPk_HouseholdSerialNumber(householdNumber);
    }
}
