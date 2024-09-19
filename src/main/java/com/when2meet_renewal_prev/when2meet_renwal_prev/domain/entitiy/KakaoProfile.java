package com.when2meet_renewal_prev.when2meet_renwal_prev.domain.entitiy;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KakaoProfile {
    @Id
    private String kakaoId; // kakaoId를 기본키로 설정
    private String nickName;
    private String profileImageUrl;
}