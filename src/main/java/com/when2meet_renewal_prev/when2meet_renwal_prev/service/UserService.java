package com.when2meet_renewal_prev.when2meet_renwal_prev.service;

import com.when2meet_renewal_prev.when2meet_renwal_prev.domain.entitiy.KakaoProfile;
import com.when2meet_renewal_prev.when2meet_renwal_prev.domain.entitiy.Party;
import com.when2meet_renewal_prev.when2meet_renwal_prev.domain.entitiy.PartyUser;
import com.when2meet_renewal_prev.when2meet_renwal_prev.domain.request.LoginRequest;
import com.when2meet_renewal_prev.when2meet_renwal_prev.repository.KakaoProfileRepository;
import com.when2meet_renewal_prev.when2meet_renwal_prev.repository.PartyRepository;
import com.when2meet_renewal_prev.when2meet_renwal_prev.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PartyRepository partyRepository; // PartyRepository 추가
    private final KakaoProfileRepository kakaoProfileRepository;

    public UserService(UserRepository userRepository, PartyRepository partyRepository, KakaoProfileRepository kakaoProfileRepository) {
        this.userRepository = userRepository;
        this.partyRepository = partyRepository;
        this.kakaoProfileRepository = kakaoProfileRepository;
    }
    // Login 필수값 검증 로직
    public ResponseEntity<String> validateLoginRequest(LoginRequest loginRequest) {
        // 필수 값 검증
        if (loginRequest.getUsername() == null || loginRequest.getPassword() == null ||
                loginRequest.getPartyId() == null) {
            // Bad Request (400) 상태 코드와 에러 메시지 반환
            return new ResponseEntity<>("Required values are not sufficient", HttpStatus.BAD_REQUEST);
        }

        // 모든 값이 유효한 경우 OK 상태 코드와 null 반환
        return new ResponseEntity<>(HttpStatus.OK);
    }
    // username과 password Party로 유저를 찾음
    public Optional<PartyUser> findUserByUsernameAndPassword(String username, String password, Party party) {
        return userRepository.findPartyUserByUsernameAndPasswordAndParty(username, password, party);

    }
    // User 생성 로직
    public PartyUser createUser(PartyUser user) {
        return userRepository.save(user);
    }
    // UserName 중복 검증
    public PartyUser validateUserName(String username) {
       return userRepository.findPartyUserByUsername(username);
    }
    // Party 조회
    public Party findPartyById(Long partyId) {
        Optional<Party> party = partyRepository.findById(partyId);
        return party.orElse(null); // 없을 경우 null 반환
    }

    //Find KakaoProfile
    public Optional<KakaoProfile> findKakaoProfileById(String kakaoProfileId) {
        return kakaoProfileRepository.findKakaoProfileByKakaoId(kakaoProfileId);
    }
}