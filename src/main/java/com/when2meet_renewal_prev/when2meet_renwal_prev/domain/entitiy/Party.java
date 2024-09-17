package com.when2meet_renewal_prev.when2meet_renwal_prev.domain.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성되도록 설정
    private Long partyId; // 이 필드는 자동으로 증가하는 ID로 설정

    private String partyTitle;
    private Date partyStartDate;
}
