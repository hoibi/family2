package com.nhnacademy.family.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "certificate_issue")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CertificateIssue {
    @Id
    @Column(name = "certificate_confirmation_number")
    private Long CertificateNumber;

    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @Column(name = "certificate_type_code")
    private String certificateType;

    @Column(name = "certificate_issue_date")
    private LocalDate certificateIssueDate;
}
