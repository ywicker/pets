package com.pets.ad.usecase;

import com.pets.ad.entity.UserAd;
import com.pets.ad.repository.AdRepository;
import com.pets.core.Usecase;
import com.pets.profil.entity.Animal;
import com.pets.profil.usecase.UserAccountRepository;

import java.time.LocalDate;

@Usecase(frenchDescription="Permet de creer une annonce depuis un compte utilisateur")
public record CreateAd(UserAccountRepository userAccountRepository, AdRepository adRepository) {

    public UserAd execute(Animal animal, LocalDate startDate, LocalDate endDate) {
        var userAd = userAccountRepository.fetchUserAccount()
                .map(userAccount -> userAccount.createAd(animal, startDate, endDate))
                .orElseThrow();
        return adRepository.save(userAd);
    }
}
