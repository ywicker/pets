package com.pets.ad.usecase;

import com.pets.ad.entity.UserAd;
import com.pets.ad.repository.AdRepository;
import com.pets.profil.entity.Address;
import com.pets.profil.entity.UserAccount;
import com.pets.profil.usecase.UserAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static com.pets.profil.entity.Animal.CAT;
import static java.util.Calendar.JULY;
import static java.util.Optional.of;

@ExtendWith(MockitoExtension.class)
class CreateAdTest {

    @Mock
    UserAccountRepository userAccountRepository;
    @Mock
    AdRepository adRepository;

    @InjectMocks
    CreateAd createAd;

    @Test
    void createAd() {
        // given
        var userAccount = new UserAccount(new Address());
        var startDate = LocalDate.of(2023, JULY, 2);
        var endDate = LocalDate.of(2023, JULY, 16);
        var animal = CAT;

        Mockito.when(userAccountRepository.fetchUserAccount()).thenReturn(of(userAccount));

        // when
        createAd.execute(animal, startDate, endDate);

        // then
        var expectedUserAd = new UserAd(userAccount, animal, startDate, endDate);
        Mockito.verify(adRepository).save(expectedUserAd);
    }
}