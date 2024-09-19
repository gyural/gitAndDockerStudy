package com.when2meet_renewal_prev.when2meet_renwal_prev.controller;

import com.when2meet_renewal_prev.when2meet_renwal_prev.domain.entitiy.KakaoProfile;
import com.when2meet_renewal_prev.when2meet_renwal_prev.domain.entitiy.Party;
import com.when2meet_renewal_prev.when2meet_renwal_prev.domain.entitiy.PartyUser;
import com.when2meet_renewal_prev.when2meet_renwal_prev.domain.request.LoginRequest;
import com.when2meet_renewal_prev.when2meet_renwal_prev.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //유저 생성
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // 필수값 검증
        ResponseEntity<String> validationResponse = userService.validateLoginRequest(loginRequest);
        if (validationResponse.getStatusCode() == HttpStatus.BAD_REQUEST) {
            return validationResponse; // 유효성 검증 실패 시 에러 반환
        }

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        Optional<String> kakaoIdOpt = loginRequest.getKakaoId();
        Long partyId = loginRequest.getPartyId();

        // Party 엔티티 찾기
        Party party = userService.findPartyById(partyId);
        if(party == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Party not found");
        }
        // 유저가 존재하는지 확인
        Optional<PartyUser> userOpt = userService.findUserByUsernameAndPassword(username, password, party);
        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        }

        // 유저가 존재하지 않으면 새 유저 생성
        PartyUser newUser = new PartyUser();
        newUser.setUsername(username);
        //중복유저라면 User생성불가
        if(userService.validateUserName(username) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }
        newUser.setPassword(password);
        newUser.setParty(party); // partyId로 Party 엔티티 설정

        // 카카오 유저라면 해당 정보 등록
        if (kakaoIdOpt.isPresent()) {
            Optional<KakaoProfile> kakaoProfileOpt = userService.findKakaoProfileById(kakaoIdOpt.get());
            kakaoProfileOpt.ifPresent(newUser::setKakaoProfile);
        }

        // 새로운 유저 생성 및 반환
        try {
            PartyUser createdUser = userService.createUser(newUser);
            System.out.println(createdUser.getHashedId());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (Exception e) {
            // 예외 발생 시 에러 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user: " + e.getMessage());
        }
    }
}