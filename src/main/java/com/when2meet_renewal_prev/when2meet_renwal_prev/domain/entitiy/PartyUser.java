package com.when2meet_renewal_prev.when2meet_renwal_prev.domain.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PartyUser {

    @Id
    private String hashedId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    private Party party;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "kakao_profile_id")
    private KakaoProfile kakaoProfile;

    private String username;
    private String password;

    @PrePersist
    protected void onCreate() {
        if (this.hashedId == null) {
            this.hashedId = UUID.randomUUID().toString();
        }
    }
}