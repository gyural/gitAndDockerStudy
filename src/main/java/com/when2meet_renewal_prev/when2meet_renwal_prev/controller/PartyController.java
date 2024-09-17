package com.when2meet_renewal_prev.when2meet_renwal_prev.controller;

import com.when2meet_renewal_prev.when2meet_renwal_prev.domain.entitiy.Party;
import com.when2meet_renewal_prev.when2meet_renwal_prev.service.PartyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/party")
public class PartyController {

    private final PartyService partyService;

    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    // 파티 생성
    @PostMapping("/create")
    public ResponseEntity<Party> createParty(@RequestBody Party party) {
        try {
            Party createdParty = partyService.createParty(party);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdParty);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(party); // 입력 데이터 오류
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 서버 오류
        }
    }

    // 파티 조회
    @GetMapping("/{id}")
    public ResponseEntity<Party> getParty(@PathVariable Long id) {
        try {
            Party party = partyService.getParty(id);
            return ResponseEntity.ok().body(party);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    // 전체 파티 조회
    @GetMapping
    public ResponseEntity<List<Party>> getAllParties() {
        try{
            List<Party> partyList = partyService.getAllParties();
            return ResponseEntity.ok().body(partyList);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    // 파티 업데이트
    @PutMapping("/{id}")
    public ResponseEntity<Party> updateParty(@PathVariable Long id, @RequestBody Party updatedParty) {
        try {
            Party updated = partyService.updateParty(id, updatedParty);
            return ResponseEntity.ok().body(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 파티 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParty(@PathVariable Long id) {
        try {
            partyService.deleteParty(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Party deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Party not found");
        }
    }
}