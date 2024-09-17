package com.when2meet_renewal_prev.when2meet_renwal_prev.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class partyCreate {
    private String partyTitile;
    private Date partyStartDate;
}
