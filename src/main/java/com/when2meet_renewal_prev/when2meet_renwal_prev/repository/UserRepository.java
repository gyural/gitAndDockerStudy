package com.when2meet_renewal_prev.when2meet_renwal_prev.repository;

import com.when2meet_renewal_prev.when2meet_renwal_prev.domain.entitiy.Party;
import com.when2meet_renewal_prev.when2meet_renwal_prev.domain.entitiy.PartyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<PartyUser, Long> {
    Optional<PartyUser> findPartyUserByUsernameAndPasswordAndParty(String username, String password, Party party);
    PartyUser findPartyUserByUsername(String username);
}
