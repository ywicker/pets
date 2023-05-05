package com.pets.ad.entity;

import com.pets.profil.entity.Animal;
import com.pets.profil.entity.UserAccount;

public record UserAd(UserAccount userAccount, Animal animal, java.time.LocalDate startDate, java.time.LocalDate endDate) {
}
