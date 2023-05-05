package com.pets.ad.usecase;

import com.pets.ad.entity.UserAd;
import com.pets.ad.repository.AdRepository;
import com.pets.core.documentation.Usecase;
import com.pets.core.exception.InconsistentPeriodException;
import com.pets.profil.entity.Animal;
import com.pets.profil.usecase.UserAccountRepository;
import com.pets.core.exception.StartDatePassedException;
import com.pets.core.repository.DateRepository;

import java.time.LocalDate;

@Usecase(frenchDescription="Permet de creer une annonce depuis un compte utilisateur")
public record CreateAd(UserAccountRepository userAccountRepository, AdRepository adRepository, DateRepository dateRepository) {

    public UserAd execute(Animal animal, LocalDate startDate, LocalDate endDate) throws StartDatePassedException, InconsistentPeriodException {

        if(startDate.isAfter(endDate) || startDate.isEqual(endDate)){
            throw new InconsistentPeriodException("");
        }
        if(startDate.isBefore(dateRepository.now())){
            throw new StartDatePassedException("");
        }

        var userAd = userAccountRepository.fetchUserAccount()
                .map(userAccount -> userAccount.createAd(animal, startDate, endDate))
                .orElseThrow();
        return adRepository.save(userAd);
    }
}
