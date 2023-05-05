package com.pets.profil.entity;

import com.pets.ad.entity.UserAd;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public record UserAccount(Address address) {
    public UserAd createAd(Animal animal, LocalDate startDate, LocalDate endDate) {
        var kibbleCost = startDate.until(endDate, DAYS);
        return new UserAd(this, animal, startDate, endDate, kibbleCost);
    }
}
