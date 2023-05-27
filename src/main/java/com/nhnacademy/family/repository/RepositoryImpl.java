package com.nhnacademy.family.repository;

import com.nhnacademy.family.entity.BirthDeathReport;
import com.nhnacademy.family.entity.QBirthDeathReport;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


public class RepositoryImpl extends QuerydslRepositorySupport implements RepositoryCustom {

    public RepositoryImpl() {
        super(BirthDeathReport.class);
    }

    @Override
    public BirthDeathReport findBirthDeathReportByBirthDeathPk_ResidentNumberAndBirthDeathPk_TypeCode(Integer residentNumber, String typeCode) {
        QBirthDeathReport qBirthDeathReport = QBirthDeathReport.birthDeathReport;


        return from(qBirthDeathReport)
                .where(qBirthDeathReport.birthDeathPk.residentNumber.eq(residentNumber))
                .where(qBirthDeathReport.birthDeathPk.typeCode.eq(typeCode))
                .select(qBirthDeathReport)
                .fetchOne();
    }
}
