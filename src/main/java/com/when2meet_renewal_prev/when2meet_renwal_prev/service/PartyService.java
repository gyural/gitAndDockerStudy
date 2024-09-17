package com.when2meet_renewal_prev.when2meet_renwal_prev.service;

import com.when2meet_renewal_prev.when2meet_renwal_prev.domain.entitiy.Party;
import com.when2meet_renewal_prev.when2meet_renwal_prev.repository.PartyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyService {

    private final PartyRepository partyRepository;

    public PartyService(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    // 파티 생성 로직
    public Party createParty(Party party) {
        validateParty(party);  // 필수 값 검증
        return partyRepository.save(party);
    }

    // 필수 값 검증 로직
    private void validateParty(Party party) {
        if (party.getPartyTitle() == null || party.getPartyTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Party title must not be null or empty.");
        }

        if (party.getPartyStartDate() == null) {
            throw new IllegalArgumentException("Party start date must not be null.");
        }

        // 추가 검증 로직 필요 시 추가 가능
    }

    // 파티 조회 로직
    public Party getParty(Long id) {
        return partyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Party not found with id " + id));
    }

    // 파티 업데이트 로직
    public Party updateParty(Long id, Party updatedParty) {
        Party existingParty = partyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Party not found with id " + id));

        existingParty.setPartyTitle(updatedParty.getPartyTitle());
        existingParty.setPartyStartDate(updatedParty.getPartyStartDate());

        return partyRepository.save(existingParty);
    }

    // 파티 삭제 로직
    public void deleteParty(Long id) {
        if (!partyRepository.existsById(id)) {
            throw new RuntimeException("Party not found with id " + id);
        }
        partyRepository.deleteById(id);
    }

    //전체 파티 조회
    public List<Party> getAllParties() {
        return partyRepository.findAll();
    }
}