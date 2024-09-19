package com.when2meet_renewal_prev.when2meet_renwal_prev.repository;

import com.when2meet_renewal_prev.when2meet_renwal_prev.domain.entitiy.KakaoProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KakaoProfileRepository extends JpaRepository<KakaoProfile, Long> {
    Optional<KakaoProfile> findKakaoProfileByKakaoId(String id);
}
