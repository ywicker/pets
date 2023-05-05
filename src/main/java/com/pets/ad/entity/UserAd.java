package com.pets.ad.entity;

import com.pets.profil.entity.Animal;
import com.pets.profil.entity.UserAccount;

import java.time.LocalDate;

public record UserAd(
        UserAccount userAccount,
        Animal animal,
        LocalDate startDate,
        LocalDate endDate,
        long kibbleCost) {
}
