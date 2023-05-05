package com.pets.profil.entity;

import com.pets.ad.entity.UserAd;

import java.time.LocalDate;

public record UserAccount(Address address) {
    public UserAd createAd(Animal animal, LocalDate startDate, LocalDate endDate) {
        return new UserAd(this, animal, startDate, endDate);
    }
}
